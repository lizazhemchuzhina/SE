# ML logs analyzer
### Project presentation
https://docs.google.com/presentation/d/1QaCWYwD5QQYgBTRzfzJL7RVKDnLpcdrF-FIhumKr3gk/edit?usp=sharing

### Docker

To build and run tests run command from root of the repository:

`docker build . -f dockerfile/build_project.Dockerfile`

To build application run from the root of the repository:

`docker build . -t <image_name> -f dockerfile/build_environment.Dockerfile`

Then run:

`docker run <image_name>`

After that you will see text: `Welcome to the simplest application!`
