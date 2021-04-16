# Getting Started

## Install [Git](https://git-scm.com/downloads) on your local system
- Start Git Shell (DOS) or Git Bash (Unix)
- Configure your name and e-mail:
```shell
 git config --global user.name "Your name"
 git config --global user.email "Your email"
```
- Clone your team repository
 ```shell
git clone https://github.com/PCC-CIS-234A/cis234a_team_JK_LOL.git
```

***

## Setup local repository

- Update your local repository
 ```shell
     git pull
```
- Open the project
- Create a new project, selecting the repository as the location,
  and it should pick up the existing source files
- Create a branch for your work:
```shell
     git branch "branchname"
     git checkout "branchname"
```
- Add your code to the project in your designated package(s) using your IDE.
- Commit & push as you go
```shell
 git add "filesThatChanged"
 git commit –m "message on what you changed"
 git push origin "branchname"
```
- Create a pull request on GitHub when a feature is complete

***

## Download Java Development Kit (JDK) in IntelliJ

The easiest way is to open Intellij and add/download from there.  Here are some instructions:
https://www.jetbrains.com/help/idea/sdk.html#jdk

Make sure you download OpenJDK version 16!
