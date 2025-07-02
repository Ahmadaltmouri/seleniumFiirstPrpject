// استيراد مكتبة Selenium لتحديد العناصر
import org.openqa.selenium.By;
// استيراد كائن WebDriver للتحكم بالمتصفح
import org.openqa.selenium.WebDriver;
// استيراد كائن WebElement لتمثيل عناصر HTML
import org.openqa.selenium.WebElement;
// استيراد WebDriver لمتصفح Edge
import org.openqa.selenium.edge.EdgeDriver;

// استيراد تعليمة BeforeTest من TestNG
import org.testng.annotations.BeforeTest;
// استيراد تعليمة AfterTest من TestNG
import org.testng.annotations.AfterTest;
// استيراد تعليمة Test من TestNG
import org.testng.annotations.Test;

// تعريف الكلاس الرئيسي للاختبارات
public class mytest {
	// إنشاء متغير من نوع WebDriver باستخدام متصفح Edge
	WebDriver driver = new EdgeDriver();

	// هذا الميثود يتم تنفيذه قبل أي اختبار (تهيئة)
	@BeforeTest
	public void mySetup() {
		// فتح موقع Saucedemo
		driver.get("https://www.saucedemo.com/");
		// تكبير نافذة المتصفح
		driver.manage().window().maximize();
	}

	// اختبار تسجيل الدخول – الأولوية رقم 1
	@Test(priority = 1)
	public void login() {
		// تحديد حقل اسم المستخدم
		WebElement UserNameInputField = driver.findElement(By.id("user-name"));
		// تحديد حقل كلمة المرور
		WebElement PasswordInputField = driver.findElement(By.id("password"));
		// تحديد زر تسجيل الدخول
		WebElement LoginButton = driver.findElement(By.id("login-button"));

		// إدخال اسم المستخدم
		UserNameInputField.sendKeys("standard_user");
		// إدخال كلمة المرور
		PasswordInputField.sendKeys("secret_sauce");
		// الضغط على زر تسجيل الدخول
		LoginButton.click();
	}

	// اختبار إضافة منتجات للسلة – الأولوية رقم 2
	@Test(priority = 2)
	public void addToCart() throws InterruptedException {
		// تأخير مؤقت لضمان تحميل الصفحة (ليس أفضل طريقة، لكن تعمل مؤقتًا)
		Thread.sleep(2000);

		// تحديد أول منتج
		WebElement FirstItem = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
		// تحديد ثاني منتج
		WebElement SecondItem = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));

		// الضغط لإضافة المنتجين إلى السلة
		FirstItem.click();
		SecondItem.click();
	}

	// اختبار عملية الشراء (Checkout) – الأولوية رقم 3
	@Test(priority = 3)
	public void CheckoutProcess() {
		// الذهاب لصفحة السلة عن طريق الضغط على أيقونة السلة
		driver.findElement(By.className("shopping_cart_link")).click();

		// تحديد زر Checkout والضغط عليه
		WebElement CheckoutButton = driver.findElement(By.id("checkout"));
		CheckoutButton.click();

		// تحديد الحقول الخاصة بالمعلومات الشخصية في صفحة الشراء
		WebElement firstName = driver.findElement(By.id("first-name"));
		WebElement lastName = driver.findElement(By.id("last-name"));
		WebElement PostalCode = driver.findElement(By.id("postal-code"));

		// إدخال البيانات المطلوبة
		firstName.sendKeys("ahmad");
		lastName.sendKeys("anas");
		PostalCode.sendKeys("38237");

		// الضغط على زر المتابعة
		WebElement ContinueButton = driver.findElement(By.name("continue"));
		ContinueButton.click();

		// الضغط على زر إنهاء الشراء
		WebElement FinishButton = driver.findElement(By.name("finish"));
		FinishButton.click();
	}

	// اختبار حذف المنتجات من السلة (تم تعطيله باستخدام enabled=false)
	@Test(priority = 3, enabled = false)
	public void removeFromCart() throws InterruptedException {
		// تأخير مؤقت
		Thread.sleep(2000);

		// تحديد زر الحذف لكل منتج
		WebElement firstItem = driver.findElement(By.id("remove-sauce-labs-backpack"));
		WebElement SecondItem = driver.findElement(By.id("remove-sauce-labs-fleece-jacket"));

		// تنفيذ الحذف
		firstItem.click();
		SecondItem.click();
	}

	// اختبار إعادة إضافة المنتجات بعد حذفها (معطل حالياً)
	@Test(priority = 4, enabled = false)
	public void addToCartAfterTheRemove() throws InterruptedException {
		// تأخير مؤقت
		Thread.sleep(2000);

		// تحديد نفس المنتجات مرة أخرى
		WebElement firstItem = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
		WebElement SecondItem = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));

		// إعادة إضافتهم إلى السلة
		firstItem.click();
		SecondItem.click();
	}

	// اختبار تسجيل الخروج (معطل حالياً)
	@Test(priority = 5, enabled = false)
	public void logout() throws InterruptedException {
		// فتح القائمة الجانبية
		WebElement BurgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
		BurgerMenu.click();

		// تأخير بسيط لضمان ظهور القائمة
		Thread.sleep(1000);

		// الضغط على زر تسجيل الخروج
		WebElement LogoutButton = driver.findElement(By.id("logout_sidebar_link"));
		LogoutButton.click();
	}

	// هذا الميثود يتم تنفيذه بعد انتهاء كل الاختبارات
	@AfterTest
	public void AfterThetestIsDone() {
		// يمكنك إغلاق المتصفح هنا
		// driver.close(); // موقوف حاليًا
	}
}

