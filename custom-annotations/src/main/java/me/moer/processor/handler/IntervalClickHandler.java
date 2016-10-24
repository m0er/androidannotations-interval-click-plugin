package me.moer.processor.handler;

import com.helger.jcodemodel.AbstractJClass;
import com.helger.jcodemodel.JBlock;
import com.helger.jcodemodel.JDefinedClass;
import com.helger.jcodemodel.JExpr;
import com.helger.jcodemodel.JFieldVar;
import com.helger.jcodemodel.JInvocation;
import com.helger.jcodemodel.JMethod;
import com.helger.jcodemodel.JMod;
import com.helger.jcodemodel.JOp;
import com.helger.jcodemodel.JPrimitiveType;
import com.helger.jcodemodel.JVar;

import org.androidannotations.AndroidAnnotationsEnvironment;
import org.androidannotations.ElementValidation;
import org.androidannotations.helper.CanonicalNameConstants;
import org.androidannotations.holder.EComponentWithViewSupportHolder;
import org.androidannotations.internal.core.handler.AbstractViewListenerHandler;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import me.moer.api.IntervalClick;

/**
 * Created by Yun on 2016. 5. 13..
 */
public class IntervalClickHandler extends AbstractViewListenerHandler {
    private JFieldVar intervalTime;
    private JFieldVar lastClickMilliseconds;
    private long intervalMilliseconds;

    public IntervalClickHandler(AndroidAnnotationsEnvironment environment) {
        super(IntervalClick.class, environment);
    }

    @Override
    public void validate(Element element, ElementValidation validation) {
        super.validate(element, validation);

        ExecutableElement executableElement = (ExecutableElement) element;

        validatorHelper.returnTypeIsVoid(executableElement, validation);

        validatorHelper.param.extendsType(CanonicalNameConstants.VIEW).optional().validate(executableElement, validation);
    }

    @Override
    public void process(Element element, EComponentWithViewSupportHolder holder) {
        IntervalClick intervalClick = element.getAnnotation(IntervalClick.class);
        intervalMilliseconds = intervalClick.intervalMilliseconds();
        super.process(element, holder);
    }

    @Override
    protected void makeCall(JBlock listenerMethodBody, JInvocation call, TypeMirror returnType) {
        JVar currentClickTime = listenerMethodBody.decl(JMod.NONE, JPrimitiveType.LONG, "currentClickMilliseconds", getCodeModel().directClass("android.os.SystemClock").staticInvoke("uptimeMillis"));
        JVar elapsedTime = listenerMethodBody.decl(JMod.NONE, JPrimitiveType.LONG, "elapsedMilliseconds", JOp.minus(currentClickTime, lastClickMilliseconds));
        listenerMethodBody._if(JOp.lte(elapsedTime, intervalTime))._then()._return();
        listenerMethodBody.assign(lastClickMilliseconds, currentClickTime);
        listenerMethodBody.add(call);
    }

    @Override
    protected void processParameters(EComponentWithViewSupportHolder holder, JMethod listenerMethod, JInvocation call, List<? extends VariableElement> parameters) {
        boolean hasItemParameter = parameters.size() == 1;

        JVar viewParam = listenerMethod.param(getClasses().VIEW, "view");

        if (hasItemParameter) {
            call.arg(castArgumentIfNecessary(holder, CanonicalNameConstants.VIEW, viewParam, parameters.get(0)));
        }
    }

    @Override
    protected JMethod createListenerMethod(JDefinedClass listenerAnonymousClass) {
        intervalTime = listenerAnonymousClass.field(JMod.PRIVATE | JMod.FINAL | JMod.FINAL, Long.class, "MIN_CLICK_INTERVAL_MILLISECONDS", JExpr.lit(intervalMilliseconds));
        lastClickMilliseconds = listenerAnonymousClass.field(JMod.PRIVATE, JPrimitiveType.LONG, "lastClickMilliseconds", JExpr.lit(0L));
        return listenerAnonymousClass.method(JMod.PUBLIC, getCodeModel().VOID, "onClick");
    }

    @Override
    protected String getSetterName() {
        return "setOnClickListener";
    }

    @Override
    protected AbstractJClass getListenerClass(EComponentWithViewSupportHolder holder) {
        return getClasses().VIEW_ON_CLICK_LISTENER;
    }

}
