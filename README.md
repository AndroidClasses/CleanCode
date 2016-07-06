[![Clean Code](https://images-na.ssl-images-amazon.com/images/I/41S371NEJXL._SX375_BO1,204,203,200_.jpg) ](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882/ref=sr_1_1?ie=UTF8&qid=1467776256&sr=8-1&keywords=clean+code)

BottomTabs
===================

Bottom Navigation tab with configurable activities. There are 3-5 TABs on the bottom navigation bar, and the count of TABs is able to be configured via a JSON file, first_page_item in the assets/ folder.

Home | Message | Find | Discovery | Me |
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/AndroidClasses/CleanCode/blob/master/app/src/main/res/drawable-xhdpi/tab_main_f.9.png)|![](https://github.com/AndroidClasses/CleanCode/blob/master/app/src/main/res/drawable-xhdpi/tab_message_f.9.png)|![](https://github.com/AndroidClasses/CleanCode/blob/master/app/src/main/res/drawable-xhdpi/tab_search_f.9.png)|![](https://github.com/AndroidClasses/CleanCode/blob/master/app/src/main/res/drawable-xhdpi/tab_search_f.9.png)|![](https://github.com/AndroidClasses/CleanCode/blob/master/app/src/main/res/drawable-xhdpi/tab__mine_f.9.png)
U can check the sample app [here](https://github.com/AndroidClasses/CleanCode/tree/master/app).

Tabs configuration
------------

You can view the configuration file online ` from GitHub's [first_page_item](https://github.com/AndroidClasses/CleanCode/blob/master/app/src/main/assets/first_page_item).

Enable all 5 TABs like this:

```JSON
{
    "MainActivity":"RecyclerSummaryV2Activity,Message_TabActivity,DiscoveryActivity,DiscoveryActivity,MineActivity"
}
```

Or enable Message, Discovery and Me only:

```JSON
{
    "MainActivity":"Message_TabActivity,DiscoveryActivity,MineActivity"
}
```

For the variants of RecyclerSummaryV2Activity, it could be replace by others options:

```
*    SummaryActivity
*    RecyclerSummaryActivity
*    RecyclerSummaryV2Activity
```

Clean Code Textbook
========

<b>Names</b>

Name are everywhere, variables, functions, arguments, classes, packages, source files and direcoties(jar, war and ear file ...), folow some rules to improve the readability of your code.

 - rule 1 Use Intention-revealing Name:

     choosing good name takes time but save more than it takes, a name should tell the reader why it exists, what it does, and how it is used, which makes it easier to understand and change code.

 - rule 2 Avoid Disinformation:

     keep words being hnonest to our intent meaning and avoid leaving false clues taht obscure the meaning of the code.

 - rule 3 Make Meaningful Distinctions:

     make different name if and only if they mean diferent things.　noninformative number-series name provid no clue to the author's intent and noise words is redundant and meaningless distinction.

 - ruel 4 Use Pronounceable Names:

     programming is a social activity, and programmer could discuss well with pronounceable name as human is good at words with concepts.

 - rule 5 Use Seachable Names:

     the name length corresponds to the size of its scope.

 - rule 6 Avoid Encoding:

     Hungarian Notation is hard to read and change and might mislead the reader. Member Prefixes becomes unseen clutter while the name of classes and functions is small enough. Interfaces and Implementations, prefer encoding the implementation name rather than the interface name.

 - rule 7 Avoid Mental Mapping:

     Clarity is king, professionals use their powers for good and write code that others can understand while smart programmers sometime like to show off their smarts by demonstrating their mental juggling abilities.

 - rule 8 Class Names:

     classes and objects should have noun or noun phrase name and should not be a verb. Prefer 'Customer', 'WikiPag'e, 'Account' or 'AddressParser'  avoid 'Manager', 'Processor', 'Data' or 'Info'.

 - rule 9 Methods Names:

     methods should have verb or verb phrase names. Accessors, mutators and predicates should be name for their value and prefixed with get, set and is according to java bean standard. User static methods with name that describe the argment for private overloaded constructors.

 - rule 10 Donnot Be  Cute:

      choose clarity over entertainment value, say what you mean and mean what you say.

 - rule 11 Pick One Word per Concept:

     A consistent lexicon is a great boon to the programmers who must use your code. Pick one word for on abstract concept and stick on it.

 - rule 12 Donnot Pun:

     Use the same word for two different purposes is essentially a pun, don't do that.

 - rule 13 Use Solution Domain Names:

     Choosing technical names for technical things is usually the most appropriate course as code readers wil lbe programmers. Go ahead and use computeer's science terms, algorithm names, pattern names, math names, and so forth.

 - rule 14 Use Problem Domain Names:

     Use the names from the problem domain if and only if there is no "programmer-eese" for what we are doing.

 - rule 15 Add Meaningful Context:

     place names in context for your reader by enclosing them in well-named classes, functions, or namespaces.

 - rule 16 Donnot Add Gratuitous Context:

     Shorter names are generally better than longer ones, so long as they are clear.


<b>Functions</b>

Functions are the first line of organization in any program, make it easy to read and understand.

* Small
* Do One Thing
* One Level of Abstraction per Function
* Switch Statements
* Use Descriptive Names
* Function Arguments
* Have No Side Effects
* Command Query Seperation
* Prefer Exception to Returning Error Code
* Don't Repeat Yourself
* Structured Programming
* How Do You Write Function Like This

<b>Comments</b>

* Comments Do Not Make Up for Bad Code
* Explain Yourself in Code
* Good Comments
* Bad Comments

<b>Formatting</b>

* The Purpose of Formatting
* Vertical Formatting
* Horizontal Formatting
* Team Rule
* Uncle Bob's Formatting Rules

<b>Objects and Data Structures</b>

* Data Abstraction
* Data/Object Anti-Symmetry
* The Law of Demeter
* Data Transfer Objects

<b>Error Handleing</b>

* Use Exception Rather Than Return Codes
* Write Your Try-Catch-Finally Statement First
* Use Unchecked Exception
* Provide Context with Exception
* Define Exception Classes in Terms of a Caller's Needs
* Don't Return Null
* Don't Pass Null

<b>Boundaries</b>

<b>Unit Tests</b>

<b>Classes</b>

<b>Systems</b>

<b>Emergence</b>

<b>Concurrency</b>

<b>Successive Refinement</b>

<b>JUnit Internals</b>

<b>Refactoring SerialDate</b>

<b>Smell and Heuristics</b>

Getting Help
======

To report a specific problem or feature request, [open a new issue on Github](https://github.com/AndroidClasses/CleanCode/issues/new).

License
======

Apache 2.0 and MIT. See [LICENSE](https://github.com/AndroidClasses/CleanCode/blob/master/LICENSE.txt) file for details.

Inspiration
======

Coming soon with a cute UI library integrating.

TapBar interactions| Circle interactions | Title interactions
:-------------------------:|:-------------------------:|:-------------------------:
![](https://s-media-cache-ak0.pinimg.com/originals/39/ee/33/39ee330f3460bd638284f0576bc95b65.gif)|![](https://s-media-cache-ak0.pinimg.com/564x/f4/0d/a9/f40da9e5b73eb5e0e46681eba38f1347.jpg)|![](https://s-media-cache-ak0.pinimg.com/564x/14/eb/dd/14ebddfc0d92f02be3d61ede48a9da6e.jpg)

Thanks to [Valery Nuzhniy](https://www.pinterest.com/hevbolt/) for NTB badge design, [DevLight Mobile Agency](https://github.com/DevLight-Mobile-Agency) for NTB implement.

Author
=======

Made in [AndroidClasses](https://github.com/AndroidClasses)

Created by [Yang Feng](https://github.com/funyoung) - [@funyoug](mailto:e13310@gmail.com)
