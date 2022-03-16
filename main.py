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
        delete_empty_file(metadata.target_path.joinpath(
            'app', 'prometheus.yml'))

        group_subpaths = metadata.global_inputs['project_group_id'].split('.')
        delete_empty_file(metadata.target_path.joinpath(
            'infra', 'src', 'main', 'kotlin', *group_subpaths, 'CloudWatchMetricsPolicy.kt'))


if __name__ == '__main__':
    run(Metrics())
