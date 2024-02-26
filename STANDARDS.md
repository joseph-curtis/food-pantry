## Versions
Our project will largely be built in **Java 16**. The project also contains a web-based component that will be built in **PHP 7.3.9** and **JavaScript ES5**. The Java portion of the project will be built in *IntelliJ IDEA version 2021.1*, and the PHP portion of the project will be built in *PHPStorm version 2020.3.3*.

## Coding Standards
For Java standards, we are following the program style guide found in the appendix of *Objects First with Java*[^fn1], with one exception. The full style guide is found here:
[BlueJ Program Style Guide Version 2.0](https://www.bluej.org/objects-first/styleguide.html)

The one change we are making is to ignore rule 2.3, which states that opening 

> braces for classes and methods are alone on one line.

In our case, we will be following rule 2.4 for all code blocks, so that opening curly braces will never stand alone on one line. For example:

    Class SampleClass {
    //code here
    }

For the portion of the project that is built in PHP and JavaScript, we will be following the PHP-FIG’s PSR-2 coding style guide for PHP (available here: [PSR-2: Coding Style Guide](https://www.php-fig.org/psr/psr-2/)) and the MDN Web Docs style guide for JavaScript (available here: [Guidelines for writing JavaScript code examples](https://developer.mozilla.org/en-US/docs/MDN/Guidelines/Code_guidelines/JavaScript)).

[^fn1]: D. J. Barnes, _Objects First with Java: A practical Introduction using BlueJ, Global Sixth Edition_. Pearson Higher Ed.

## Comment Standards
Classes will include comments with Javadoc annotations, or inline comments for the portions of the project built in PHP & JavaScript. We will follow this format:

    Description
    @author
    @version (the date)
    @param (parameters)
    @return (data type returned)

## Security Requirements
To ensure the security of our users, we have agreed upon password requirements. Passwords:
-	may include any ASCII characters
-	have a minimum of 8 and a maximum 64 characters
-	are required to include at least one number, lowercase letter, uppercase letter, and special character

## Database ERD Naming Standards
- *Table names* are **all caps** with underscores separating words:  `TABLE_NAME`
- *Primary keys* will have **CamelCase** and `PK_` in front and `_ID` on end, i.e.  `PK_NamedKey_ID`
- *Foreign keys* will have **CamelCase** and FK\_ in front and \_ID on end, i.e.  `FK_NamedKey_ID`
- *Views* and *cursors* will have `VIEW_` and `CURS_`  in front and **CamelCase**
- All *attributes* are **lowercase** with underscores

## Design Standards
To ensure unity with other PCC services, we will use the brand standard color palette as described in the style guide here: https://www.pcc.edu/web-services/style-guide/basics/color/.

We will use the brand standard colors in the following ways:
| Color Name| hex value | rgb value | Elements applied |
|--|--|--|--|
| Turquoise | #008099 | rgb(0, 128, 153) | buttons, tabs, clickable things |
Dark Turquoise | #00667a, rgb(0, 102, 122) | text links |
Amethyst | #70485b | rgb(112, 72, 91) | visited links |
Sapphire | #263c53 | rgb(38, 60, 83) | banners, headers, and sidebars |

We will use the brand standard neutral colors where otherwise appropriate:
| Color Name| hex value | rgb value |
|--|--|--|
| Light Gray | #e9e9e9 | rgb(233, 233, 233) |
| Medium Gray | #c1c1c1 | rgb(193, 193, 193) |
| Dark Gray | #333333 | rgb(51, 51, 51) |
