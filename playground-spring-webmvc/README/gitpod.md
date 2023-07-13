# GitPod

## Set up java version in workspace

- https://www.gitpod.io/docs/introduction/languages/java

`.gitpod.Dockerfile`

```yaml
FROM gitpod/workspace-full

USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && \
sdk install java 17.0.7-tem && \
sdk default java 17.0.7-tem"
```

## Configuration file `.gitpod.yml`

- https://www.gitpod.io/docs/references/gitpod-yml

## Tasks

- https://www.gitpod.io/docs/configure/workspaces/tasks

