# Read our base image from ToolForge public images
FROM docker.toolforge.io/public/ubuntu:22.04

# Set up Java 17
RUN apt-get update \
    && apt-get -y -q --no-install-recommends install openjdk-17-jre-headless

# Set up our execution environment
WORKDIR /root
COPY target/hello-toolforge.jar /root/
COPY manifest.yml /toolforge/manifest.yml

# Go!
ENTRYPOINT [ "/usr/bin/java", "-jar", "hello-toolforge.jar" ]