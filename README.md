# HELLO TOOLFORGE

This project is an example implementation of a
[ToolForge](https://app.toolforge.io/) tool in Java. It uses the
[ToolForge Maven
plugin](https://github.com/toolforgeio/toolforge-maven-plugin) to
generate its argument parser from its manifest. It also uses the
excellent [Fabric8 Docker Maven plugin](https://dmp.fabric8.io/) to
build its [Docker](https://www.docker.com/) container for pushing into
ToolForge. Simply running the following command will generate the
required Docker image:

    mvn clean compile install