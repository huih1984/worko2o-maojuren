package com.delta.worko2o.service.api;

import com.delta.worko2o.model.User;
import com.delta.worko2o.model.Employer;
import com.delta.worko2o.model.JobHunter;

/**
 * @author john
 */
public interface EmailValidServiceApi {
    void addUser(User user);

    void modUser(String token, boolean ignoreNull);

    User qryUser(User user);

    void deleteUser(User user);

    void sendEmail(User user);

    void sendResetPasswordEmail(User user);

    void sendInform(Employer employer, JobHunter jobHunter, String jobName);

    void sendCheckPassedEmail(Employer employer);
}
