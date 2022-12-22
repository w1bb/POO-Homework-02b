# POO TV - Part 1 (POO-Homework-02a)

This repository is intended to store a complex user-webpage interaction simulation. It is part of a computer
science assignment (2nd year, 1st semester).

Full completion date: ?

Deadline: 16 Jan. 2023

This project will be available <a href="https://github.com/w1bb/POO-Homework-02b">on GitHub</a> once the deadline passes.
The original homework is available on <a href="https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2">our OCW</a>, but a
copy of the text will be provided in the repo.

## License

Once the deadline passes, this project will be available under the MIT license. For more info about the author of the
code, please check out <a href="https://v-vintila.com">my personal website</a>.

## Coding style choices

There are a few design choices I want to address:
* The methods and variables will follow
  <a href="https://www.oracle.com/java/technologies/javase/codeconventions-fileorganization.html">the Oracle</a> file
  organization convention, so:
    1) Class (static) variables: First the public class variables, then the protected, and then the private.
    2) Instance variables:First public, then protected, and then private.
    3) Constructors
    4) Methods: These methods should be grouped by functionality rather than by scope or accessibility. For example, a
       private class method can be in between two public instance methods. The goal is to make reading and understanding the
       code easier.

TODO - continue this README