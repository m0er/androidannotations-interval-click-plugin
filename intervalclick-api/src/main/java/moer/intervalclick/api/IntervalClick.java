package moer.intervalclick.api;

import org.androidannotations.annotations.ResId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * This annotation extends {@link org.androidannotations.annotations.Click} annotation
 * to prevent multiple clicks in the same time period.
 * </p>
 * <p>
 * The intervalMilliseconds annotation value is optional. If not set,
 * the value will be used 600ms.
 * </p>
 * <p>
 * The method MAY have one parameter:
 * </p>
 * <ul>
 * <li>A {@link android.view.View} (or a subclass) parameter to know which view
 * has been clicked</li>
 * </ul>
 * <blockquote>
 *
 * Example :
 *
 * <pre>
 * &#064;IntervalClick(<b>R.id.myButton</b>)
 * void clickOnMyButton() {
 * 	// Something Here
 * }
 *
 * &#064;IntervalClick
 * void <b>myButton</b>Clicked(View view) {
 * 	// Something Here
 * }
 *
 * &#064;IntervalClick
 * void <b>myButton</b>Clicked(Button view) {
 * 	// Something Here
 * }
 *
 * &#064;IntervalClick(intervalMilliseconds = 3000)
 * void <b>myButton</b>() {
 * 	// Something Here
 * }
 * </pre>
 *
 * </blockquote>
 *
 * @see org.androidannotations.annotations.Click
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface IntervalClick {

    /**
     * The R.id.* fields which refer to the Views.
     *
     * @return the ids of the Views
     */
    int[] value() default ResId.DEFAULT_VALUE;

    /**
     * The resource names as strings which refer to the Views.
     *
     * @return the resource names of the Views
     */
    String[] resName() default "";

    /**
     * The time period to prevent multiple clicks.
     *
     * @return the values of time period.
     */
    long intervalMilliseconds() default 600;

}
