# POO TV - Part 2

This repository is intended to store a complex user-website interaction simulation.
It is part of a computer science assignment (POO / 2nd year, 1st semester).

**Full completion date:** 03 Jan. 2023

**Deadline:** 16 Jan. 2023

This project will be available [on Github](https://github.com/w1bb/POO-Homework-02b) once the
deadline passes. The original homework is available on
[our OCW](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2), but a copy of the text shall
be provided in this repository.

> **Note:** The last date the links were accessed is: 03 Jan. 2023.

## License

Once the deadline passes, this project will be available under the
[MIT license](https://github.com/w1bb/POO-Homework-02b/blob/master/LICENSE). For more info about the
author of the code, please check out [my personal website](https://v-vintila.com). Other interesting
projects are made available on [my GitHub page](https://github.com/w1bb) (a follow would be greatly
appreciated!).

## Table of contents

* [Design patterns](#design-patterns)

## Design patterns

> In the previous stage of the project, even when the design patterns were outlined separately, to
> this day, they **did not help the project receive any bonuses** (at that point, four design
> patterns were implemented). For this reason, this section was moved up a little, in order to help
> the team correctly identify them.

All the design patterns used throughout the project are explained in this section.

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

More information about the strategy design pattern can be found
[here](https://en.wikipedia.org/wiki/Strategy_pattern).

## Coding style choices

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