# MarketBasket
A Java Market Basket Implementation with Taxes calculation.

## Architecture
The following Architecture was thinking on acess logic through Business layer, were the ```Checkout.java``` is the Business Object, that acess components needed in order to achieve the Checkout.

These data would be passed to a superior layer that could be a Controller, or in that case, the Application.java itself.

Models contain entities that could be schema for database objects.

DTO represents the data transfer objects that are being used for intermediate data transfer operations between components.

Utils contains all utilitary classes that could be used along te project.

Tests folder follows the same architecture to unit test business logic.

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┣ 📂core
 ┃ ┃ ┃ ┣ 📂business
 ┃ ┃ ┃ ┃ ┣ 📂checkout
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TaxesCalculator.java
 ┃ ┃ ┃ ┃ ┗ 📜Checkout.java
 ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┣ 📜BuyOrder.java
 ┃ ┃ ┃ ┃ ┣ 📜Receipt.java
 ┃ ┃ ┃ ┃ ┣ 📜TaxedOrder.java
 ┃ ┃ ┃ ┃ ┗ 📜TaxedProduct.java
 ┃ ┃ ┃ ┣ 📂models
 ┃ ┃ ┃ ┃ ┣ 📂enums
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ProductType.java
 ┃ ┃ ┃ ┃ ┗ 📜Product.java
 ┃ ┃ ┃ ┗ 📜Application.java
 ┃ ┃ ┗ 📂utils
 ┃ ┃ ┃ ┗ 📜MathUtils.java
 ┃ ┗ 📂resources
 ┗ 📂test
 ┃ ┣ 📂java
 ┃ ┃ ┣ 📂core
 ┃ ┃ ┃ ┗ 📂business
 ┃ ┃ ┃ ┃ ┣ 📂checkout
 ┃ ┃ ┃ ┃ ┃ ┗ 📜TaxesCalculatorTest.java
 ┃ ┃ ┃ ┃ ┗ 📜CheckoutTest.java
 ┃ ┃ ┗ 📂TestUtils
 ┃ ┃ ┃ ┗ 📜TestUtils.java
 ┃ ┗ 📂resources
 ```

 ## How to Run
 Run the Application.java main method.