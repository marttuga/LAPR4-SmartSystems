package eapli.base.ordersmanagement.answer.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordersmanagement.answer.domain.Answer;
import eapli.base.ordersmanagement.answer.repository.AnswerRepository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnswerService {

    private final AnswerRepository answerRepository = PersistenceContext.repositories().answer();

    public int universe(String id){
        List<Answer> answers = answerRepository.universe(id);
        return answers.size();
    }

    public int answers(String id){
        List<String> answer = getAllAnswers(id);
        answer.removeIf(s -> s.equalsIgnoreCase("NOT ANSWERED"));
        return answer.size();
    }

    public List<String> getAllAnswers(String id){
        List<Answer> answers = answerRepository.universe(id);
        Map<String, String> map = new HashMap<>();
        List<String> answer = new ArrayList<>();
        for (Answer a: answers) {
            map=a.getEvery_Answer();
            for (var entry: map.entrySet()) {
                answer.add(entry.getValue());
            }
        }
        return answer;
    }



    public double calculatePercentage (String id){
        double total = getAllAnswers(id).size();
        double answered = answers(id);
        double result =  answered * 100 / total;
        return Math.round(result);
    }

    public List<Answer> getAllAnswersToList(String id){
        return  answerRepository.universe(id);
    }

}
