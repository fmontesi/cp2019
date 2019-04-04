# Exam Instructions

To pass the course, you have to prepare a software project and a written report
on it. You have to put them in two separate files:
- `source.zip`: a zip file containing the source code of the software project;
- `report.pdf`: this is the written report.

Detailed instructions on the two items are given below. **Pay attention to the upload instructions at the end of this document! If you do not follow them, you risk failing automatically!**

You can find a list of common mistakes that people do in the exam for this course in [common mistakes](https://github.com/fmontesi/cp2019/blob/master/exam/common-mistakes.md). Please read it after reading the following instructions.

## Software project (source.zip)

The `source.zip` file must contain the Java files given in the directory `exam-project/src` of this repository.
You can add new files if you wish, but you cannot change the names of the existing files. You cannot modify the given interfaces.

Your task is to implement the methods of class `Exam`. You will find information on what the methods are supposed to do
in the source code of class `ExamInterface`. Implementing `Exam` will require that you create a class that implements the interface `Result` and a class that implements the interface `Stats`. You can choose the names of your new classes as you wish (but never change the names of existing classes!).


*IMPORTANT:*
- You can find a directory that you can use to test your project in the directory `data_example`, located in the same directory as this readme file. The directory I will use in the evaluation will follow the same format, but it will be different.
- You can add your own classes, but you cannot change the types of what the the
methods take as parameters or return. You cannot change the provided
interfaces in any way. If you do, you risk failing the exam automatically.
- When you start implementing a method in `Exam`, remove the `throw new UnsupportedOperationException();` line from it first. If you do not manage to
implement a method, then keep that line instead so that I know you chose not
to give an implementation for that particular method.
- You do not need to implement all methods to pass, but you need at least a good
implementation of one of them. If you implement only one method and it does
not work well, then you risk failing.
- If you are taking the re-exam for the 5 ECTS version of this course, you need only to implement methods `m1` and `m2` and you can disregard errors on method `m3`.


## Checking your project (you must do this at least once!)

Go to [https://concurrency.sdu.dk/cp-tester](https://concurrency.sdu.dk/cp-tester).
You will find a webpage where you can upload your `source.zip` file and get information on whether it satisfies the format of the exam, along with some useful information on its correctness.

Every time you test your project, the webpage will give you a unique **exam identifier**.
Remember to save the last exam identifier that you get, this is important for the report (explained later).


## Report (report.pdf)

The report must be written in English and be at max 3 page long.

Use 1.5 line spacing and ensure that the page margins are at least of 2cm (in all directions).
For the body text, use Times new roman as family and at least 11pt as font size.

If you are using LaTeX, an easy way to obtain this is:
```
\documentclass[11pt]{article}
\usepackage[a4paper,top=2cm,bottom=2cm,left=2cm,right=2cm]{geometry}
\usepackage{times}

% ... rest of your preamble before the document ...

\begin{document}
\setlength{\baselineskip}{1.44\baselineskip} % This sets the line spacing to something that looks like 1.5 in Word

\end{document}
```

In the first page of the report, you must state
the name of the course, your name, your e-mail address, the date (in which
you last edited the document), and your **exam identifier** (which you got from the online project checker).
The report must contain the following sections:
- Methodology. Here you explain how you designed your software. Give a brief
  overview of how it works and then focus on how you handle concurrency.
  For example, did you use futures, executors, manual control over threads,
  and/or monitors? How did you use them?
- Advantages. Here you explain the nice points of your implementation, i.e.,
  the advantages that come out of your design choices.
- Limitations. Here you explain the limitations of your approach and, possibly,
  how they could be overcome. Did you sacrifice performance for achieving better
  readability of your code? Or, did you sacrifice readability for improving
  efficiency? Is there a way to improve how you coordinate concurrent computation?

Inspiration points to discuss advantages and
limitations:
- Readability
- Speed
- Scalability (scales well with the number of cores, or amount of memory)
- Reusability (the code can be reused in different contexts)
- Memory consumption
- Reliability (handling of errors/exceptions)


# Upload instructions (exam hand-in)

An assignment will be opened on Blackboard for handing in the project. The deadline for uploading the project on Blackboard will be: 31 May 2019 at midnight (local time).

The file that you upload on Blackboard must be a zip file containing exactly the following two files.
- `source.zip`: the zip file containing the source code of your software project. **This must be exactly the same file that you uploaded in the online project checker for the exam identifier that you wrote in the report, otherwise the identifiers will not match!**
- `report.pdf`: this is the written report. **The exam identifier that you got from the online project checker for your `source.zip` file must be in the front page!**

# Frequently Asked Questions and Comments

- Including external libraries is forbidden.
You can only use the Java standard library.

- Be careful with thread termination: when I measure the time it takes your method calls to terminate, having threads that have not terminated may slow down the measurement.

- How do I traverse directories?
There are different ways to traverse directories in Java. See: [http://www.adam-bien.com/roller/abien/entry/listing_directory_contents_with_jdk](http://www.adam-bien.com/roller/abien/entry/listing_directory_contents_with_jdk) and [https://docs.oracle.com/javase/tutorial/essential/io/walk.html](https://docs.oracle.com/javase/tutorial/essential/io/walk.html). There is no "best" way for the project, since maybe one way will play nicer than the others with how you intend to program concurrency. You can also see the (simple!) example in `lectures`.

- What encoding will the files be in?
UTF-8. I will not use any weird characters that require thinking of more than a Java `char`. I do not recommend using byte representations of strings.

- This is useful if you want to test how your project scales with respect to the number of cores: How do I de-activate CPU cores in Linux?
See [http://www.cyberciti.biz/faq/debian-rhel-centos-redhat-suse-hotplug-cpu/](http://www.cyberciti.biz/faq/debian-rhel-centos-redhat-suse-hotplug-cpu/).
