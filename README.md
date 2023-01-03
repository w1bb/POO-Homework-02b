# POO TV ~ Stage II

This repository is intended to store a complex user-website interaction simulation.
It is part of a computer science assignment (POO / 2nd year, 1st semester).

You can check out the first stage of the project [here](https://github.com/w1bb/POO-Homework-02a).

**Full completion date:** 03 Jan. 2023

**Deadline:** 16 Jan. 2023

This project will be available [on Github](https://github.com/w1bb/POO-Homework-02b) once the
deadline passes. The original homework is available on
[our OCW](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2), but a copy of the text shall
be provided in this repository.

## License

Once the deadline passes, this project will be available under the
[MIT license](https://github.com/w1bb/POO-Homework-02b/blob/master/LICENSE). For more info about the
author of the code, please check out [my personal website](https://v-vintila.com). Other interesting
projects are made available on [my GitHub page](https://github.com/w1bb) (a follow would be greatly
appreciated!).

## Table of contents

* [Design patterns](#design-patterns)
* [Coding style convention](#coding-style-convention)
* [Documentation](#documentation)

## Design patterns

> In the previous stage of the project, even when the design patterns were outlined separately, to
> this day, they **did not help the project receive any bonuses** (at that point, four design
> patterns were implemented). For this reason, this section was moved up a little, in order to help
> the POO team correctly identify them and mark them.

All the design patterns used throughout the project are explained in this section. All of them
are actually further explained in the **"Design Patterns: Elements of Reusable Object-Oriented
Software"** book (written by Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides).

For a better readability, articles for each design pattern were provided.

### 1. The SINGLETON design pattern

Pages are static and _(usually)_ independent of the logged-in user. For this reason, all the pages
are actually instantiated only once, thus implementing the **singleton design pattern**.

More information about the singleton design pattern can be found
[here](https://www.tutorialspoint.com/design_pattern/singleton_pattern.htm).

### 2. The BUILDER design pattern

Whenever a page is queried, it responds with a custom _page response_ - this structure is created
using a builder, thus making use of the **builder design pattern**.

More information about the builder design pattern can be found
[here](https://refactoring.guru/design-patterns/builder).

### 3. The STRATEGY design pattern

Whenever the interpreter is asked to follow a request, a structure (called a _page request_) is
generated and sent over to the appropriate sub-interpreter, and from there, it is sent to the
correct page. This makes use of the **strategy design pattern**.

More information about the strategy design pattern can be found
[here](https://en.wikipedia.org/wiki/Strategy_pattern).

### 4. The FACADE design pattern

The `Interpreter` class is a great example of making use of the **facade design pattern** - the
class hides the complexity of the whole action-processing and provides a simple and easy to use API
comprised of a constructor and a `runActions()` method.

More information about the facade design pattern can be found
[here](https://refactoring.guru/design-patterns/facade).

### 5. The FACTORY design pattern

A certain page can be generated _(since singleton is used, it will simply be returned instead)_
using its name. This comes in handy whenever a `change page` action is queried. This is the
reasoning behind `PageFactory`, a class that makes use of the **factory design pattern**.

More information about the facade design pattern can be found
[here](https://www.tutorialspoint.com/design_pattern/factory_pattern.htm).

## Coding style convention

### Oracle's file organization convention

The methods and variables will follow
[Oracle's](https://www.oracle.com/java/technologies/javase/codeconventions-fileorganization.html)
file organization convention, so:
1) Class (static) variables: First the public class variables, then the protected, and then the
   private.
2) Instance variables:First public, then protected, and then private.
3) Constructors
4) Methods: These methods should be grouped by functionality rather than by scope or
   accessibility. For example, a private class method can be in between two public instance
   methods. The goal is to make reading and understanding the code easier.

### `toString()` best practice

Some students were **wrongly** marked down for implementing this method (and I want to call out
Mihai Soare, he is responsible for doing this).

Since it is best practice to implement a `toString()` method, important classes that required it
during the debugging stages of the project might still have them implemented to this day.
[It](http://www.javapractices.com/topic/TopicAction.do?Id=55)
[is](https://www.baeldung.com/java-tostring)
[best](https://www.infoworld.com/article/2073619/java-tostring---considerations.html)
[practice](https://kylec32.medium.com/effective-java-override-tostring-4c1ba07e0bd2)!
Even [the docs](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#toString()) say that
_"It is recommended that all subclasses override this method"_!

## Documentation

### Interpreters

In short, the control is passed down from the `Main` class to the `Interpreter` class - the latter
piece of code _understands_ the commands and translates them to an actual user-website interaction.
There are multiple subinterpreters used throughout the project that split the work based on its
actual meaning, including:

* `BackInterpreter` - specifically designed for the `back` command;
* `ChangePageInterpreter` - called whenever a `change page` action is to be resolved;
* `DatabaseInterpreter` - used when the administrator has to update the movie database;
* `OnPageInterpreter` - called when a `on page` action is queried.

All of these interpreters implement the `GeneralInterpreter` interface, which exposes the
`executeAction()` interface required everywhere.

### Individuals vs databases

Users and movies are stored in two different ways:
* As individual entities (the `User` and `Movie` classes) - this is their main representation, but
  this might not always suffice;
* As collective entities (the `UsersDB` and `MoviesDB` classes) - these are the database
  representations of the individual instances.

This way, whenever a mass of users has to be notified / a slice of the movies database is
requested (e.g. filtered), the interaction is seamless.

### Notifications

A notification, as of right now, is only intended to hold information about a specific movie, be it
a new arrival, a deletion or a recommendation. Thus, whilst multiple notification types are possible
(check out the code for `NotificationType`), notifications should only hold the information about a
movie and  the notification type itself.

### Pages

Pages are naturally split based on their required login status, meaning that the following packages
arise:
* `unauth` - intended to store pages on which the used should be unauthenticated:
  * `UnauthHomePage` (internally called `unauth-homepage`) - the homepage for unauthenticated users;
  * `LoginPage` (internally called `login`) - the page that allows a user to log in to the platform;
  * `RegisterPage` (internally called `register`) - the page that allows a user to register to the
    platform;
* `auth` - intended to store pages on which the user has to be authenticated:
  * `AuthHomePage` (internally called `auth-homepage`) - the homepage for authenticated users;
  * `LogoutPage` (internally called `logout`) - the page that redirects the user to
    `unauth-homepage`;
  * `MoviesPage` (internally called `movies`) - the page that holds the information about the movie
    database;
  * `SeeDetailsPage` (internally called `see details`) - the page that holds the information about a
    specific movie;
  * `UpgradesPage` (internally called `upgrades`) - the page that allows a user to upgrade to a 
    premium account or buy tokens.

A `PageFactory` class is provided that converts the internal name of a page into the actual instance
of the class (there is a single instance of each page since the singleton design pattern is used).

The interpreter(s) communicate with the pages via `PageQuery` instances and the pages communicate
back to the `Interpreter` via `PageResponse` instances.
