
# Generate dependency updates report

```shell
mvn versions:dependency-updates-aggregate-report -DonlyProjectDependencies=true
```
[see documentation](https://www.mojohaus.org/versions/versions-maven-plugin/dependency-updates-aggregate-report-mojo.html)

# Update dependencies

```shell
mvn versions:use-next-versions
```
[see documentation](https://www.mojohaus.org/versions/versions-maven-plugin/use-next-versions-mojo.html)


# Generate parent update report

```shell
mvn versions:parent-updates-report
```
[see documentation](https://www.mojohaus.org/versions/versions-maven-plugin/parent-updates-report-mojo.html)
