from pathlib import Path

from templateframework.metadata import Metadata
from templateframework.runner import run
from templateframework.template import Template

REACTIVE_TEMPLATE = 'reactive-spring-kotlin-template'


def delete_empty_file(path: Path):
    if path.is_file() and path.stat().st_size == 0:
        path.unlink()


class Metrics(Template):
    def pre_hook(self, metadata: Metadata):
        results = filter(lambda template: template.template_data_path.endswith(REACTIVE_TEMPLATE),
                         metadata.history_folder.load_history().applied_templates)
        if next(results, None) is not None:
            metadata.computed_inputs['dependency'] = 'org.springframework.boot:spring-boot-starter-webflux'
        else:
            metadata.computed_inputs['dependency'] = 'org.springframework.boot:spring-boot-starter-web'
        return metadata

    def post_hook(self, metadata: Metadata):
        base_path = metadata.target_path.joinpath('app')
        delete_empty_file(base_path.joinpath('prometheus.yml'))

        group_id_subpaths = metadata.global_inputs['project_group_id'].split(
            '.')
        configuration_path = base_path.joinpath(
            'src', 'main', 'kotlin', *group_id_subpaths)
        delete_empty_file(configuration_path.joinpath(
            'CloudWatchMetricsConfiguration.kt'))
        delete_empty_file(configuration_path.joinpath(
            'CloudWatchMetricsProperties.kt'))


if __name__ == '__main__':
    run(Metrics())
