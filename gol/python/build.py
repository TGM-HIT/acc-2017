from pybuilder.core import use_plugin, init

use_plugin("python.core")
use_plugin("python.unittest")
use_plugin("python.install_dependencies")
use_plugin("python.pycharm")
use_plugin("python.sphinx")
use_plugin("python.flake8")
use_plugin("python.coverage")
use_plugin("python.distutils")

name = "gol_python"
default_task = "publish"


@init
def set_properties(project):
    project.set_property("dir_source_main_python", "src/main/python/")
    project.set_property("dir_docs", "docs")
    # Sphinx-Documentation
    project.set_property("sphinx_config_path", "docs/source")
    project.set_property("sphinx_source_dir", "docs/source")
    project.set_property("sphinx_output_dir", "docs/build")
    # Testing
    project.set_property("dir_source_unittest_python", "src/unittest/python")
    # project.set_property("nose_with-nosehtml",None)
    # project.set_property("nose_html-report-file","target/reports/testreport.html")
    project.set_property("coverage_threshold_warn", 80)
    project.set_property("coverage_branch_threshold_warn", 70)
    project.set_property("coverage_branch_partial_threshold_warn", 80)
