# ML logs analyzer [![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome#readme)
[![License](https://img.shields.io/github/license/lizazhemchuzhina/SE.svg)]()
<a href="https://github.com/lizazhemchuzhina/SE/graphs/contributors" target="_blank">
<img src="https://img.shields.io/github/contributors-anon/lizazhemchuzhina/SE.svg" alt="GitHub contributors">
</a>
![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)
## Introduction
ML logs analyzer can be used for real-time monitoring of your project's logs in order to detect any changes and prevent
failures in the future.

## Background
### Why?
We want to know if a project is about to break down before it actually happens.
### How?
Monitor logs, analyze them, notice any significant changes.
### What can you do with ML logs analyzer?
The project is currently under development, so there are certain limitations to its functionality at the moment (but please keep in touch for future updates).
1. Logs can be analyzed so that the most critical ones for failure prediction are selected.
2. Users are registered in the system and can be added to working groups that correspond to projects.
3. Logs corresponding to projects can be stored and queried.
### Project presentation
https://docs.google.com/presentation/d/1QaCWYwD5QQYgBTRzfzJL7RVKDnLpcdrF-FIhumKr3gk/edit?usp=sharing

## Project ROADMAP
https://venngage.net/ps/dym6Lzk3GPQ/ml-logs-analyzer

## Installation
Currently ML logs analyzer is available as a separate library. So you can simply clone source files and use it:

```bash
git clone https://github.com/lizazhemchuzhina/SE.git
```
### Important Examples
1. FileWrapper

Can be used to store all logs for a selected file.

Can be created by passing file path in constructor:
```bash
FileWrapper myFileWrapper = new FileWrapper("/home/user/file");
```

Logs can be added:
```bash
int logId = myFileWrapper.addLog("WARN: SOMETHING BROKE");
```

And queried:
```bash
List<Log> logList = myFileWrapper.getAllLogs();
```

2. Group

There are 3 types of users for each project:

NONAUTHORIZED : non-authorized users (have no rights to the project)

USER : authorized users with limited rights to project

ADMIN : authorized users with unlimited rights to project

3. Labels

There are 4 log labels available: ERROR, WARNING, INFO, TRACEBACK

4. Log

Can be used to store the necessary information about log, in particular: message, level and label, 
where label is either ERROR, WARNING, INFO, TRACEBACK and level means the importance of the log.

Labels are set automatically when log is created:
```bash
Log myLog = new Log("ERROR: SOMETHING BROKE");
Assert.assertEquals(myLog.getLabel(), Labels.ERROR);
```

5. User 

Stores user's credentials(login and password), their group (which corresponds to their rights limitations) 
and their working groups, where each working group corresponds to a particular project.

User can be added to unlimited number of groups:
```bash
User user = new User("login", "password");
user.addWorkingGroup("projectX");
```

6. FileService

Can be used for matching projects to corresponding FileWrappers, which store logs for files in project.
```bash
List<Log> = FileService.getLogsFromProject("projectX");
```

7. InteractionService 

Can be used for matching projects to corresponding working groups.
```bash
List<String> = InteractionService.getWorkingGroups("projectX");
```

8. LogService

Can be used for storing and managing Log objects.
```bash
LogService logService = new LogService();
int logId = logService.add(myLog);
boolean ok = logService.changeLogLevel(logId, 5);
```

9. UserService 

Can be used for user registration, authentication and general managing 
(e.g. changing of credentials, working groups; assigning user to working groups)

For example:

```bash
boolean ok = UserService.authenticate("login", "password");
Group userGroup = UserService.authorize("login");
boolean removed = UserService.removeFromWorkingGroup("login", "group");
```


## Authors
- Evgenya Kirillova (Кириллова Евгения)
- Elizaveta Zhemchuzhina (Жемчужина Елизавета)
- Elena Sunko (Сунко Елена)

## Acknowledgements
Many-many thanks to our great teachers who helped us through the whole project!
- Vladislav Tankov
- Timofey Bryksin
- Alexander Omelchenko
- Anton Kuznetsov
- Alexander Fedorov

Special gratitude to our pets who kept us cheered up!
- Sofochka
- Jerry
- Prosha
## Contributing
Pull requests are welcome. Thank you for your suggestions!

### Contribution Guidelines

Please ensure your pull request adheres to the following guidelines:

- Alphabetize your entry.
- Search previous suggestions before making a new one, as yours may be a duplicate.
- Make an individual pull request for each suggestion.
- Keep descriptions short and simple, but descriptive.
- For major changes, please open an issue first to discuss what you would like to change.

## License
[Apache License 2.0](https://choosealicense.com/licenses/apache-2.0/)

## Links
* [Issue tracker](https://github.com/lizazhemchuzhina/SE/issues)
