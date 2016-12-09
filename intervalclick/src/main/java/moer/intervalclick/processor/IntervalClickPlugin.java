package moer.intervalclick.processor;

import org.androidannotations.AndroidAnnotationsEnvironment;
import org.androidannotations.handler.AnnotationHandler;
import org.androidannotations.plugin.AndroidAnnotationsPlugin;

import java.util.ArrayList;
import java.util.List;

import moer.intervalclick.processor.handler.IntervalClickHandler;

/**
 * Created by Yun on 2016. 5. 13..
 */
public class IntervalClickPlugin extends AndroidAnnotationsPlugin {

    @Override
    public String getName() {
        return "IntervalClick";
    }

    @Override
    public List<AnnotationHandler<?>> getHandlers(AndroidAnnotationsEnvironment androidAnnotationEnv) {
        List<AnnotationHandler<?>> annotationHandlers = new ArrayList<>();
        annotationHandlers.add(new IntervalClickHandler(androidAnnotationEnv));
        return annotationHandlers;
    }
}
