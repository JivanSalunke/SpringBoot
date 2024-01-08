package learnspring.springbootdemo.TODO;

import org.springframework.stereotype.Service;

@Service
public class Validator {

    public Validator() {
    }

    public boolean validateTaskName(String taskName){
        return taskName.length()<=50;
    }
    public boolean validateTaskDescription(String taskDesc){
        return taskDesc.length()<=200;
    }
}
