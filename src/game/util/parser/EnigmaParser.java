package game.util.parser;

import game.enigma.AnswerEnigma;
import game.enigma.Enigma;
import game.enigma.QCMEnigma;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnigmaParser {

    public List<Enigma> parse(String filepath) throws IOException, ParseException {
        JSONObject json = (JSONObject) new org.json.simple.parser.JSONParser().parse(new FileReader(filepath));
        JSONArray qcmEnigmas = (JSONArray) ((JSONObject)json.get("enigmas")).get("qcm");
        JSONArray answerEnigmas = (JSONArray) ((JSONObject)json.get("enigmas")).get("answer");

        List<Enigma> enigmas = new ArrayList<>();
        enigmas.addAll(this.parseQCMEnigmas(qcmEnigmas));
        enigmas.addAll(this.parseAnswerEnigmas(answerEnigmas));

        return enigmas;
    }

    private List<Enigma> parseQCMEnigmas(JSONArray json) {
        List<Enigma> enigmas = new ArrayList<>();

        for (Object obj : json) {
            JSONObject jobj = (JSONObject) obj;
            String question = (String) jobj.get("question");
            List<String> answers = new ArrayList<>();
            String trueAnswer = "";

            for (Object ans : (JSONArray) jobj.get("answers")) {
                JSONObject answer = (JSONObject) ans;
                answers.add((String) answer.get("value"));

                if (answer.get("isAnswer").equals("true")) {
                    trueAnswer = (String) answer.get("value");
                }
            }

            enigmas.add(new QCMEnigma(question, answers, trueAnswer));
        }

        return enigmas;
    }

    private List<Enigma> parseAnswerEnigmas(JSONArray json) {
        List<Enigma> enigmas = new ArrayList<>();

        for (Object obj : json) {
            JSONObject jobj = (JSONObject) obj;
            String question = (String) jobj.get("question");
            String answer = (String) jobj.get("answer");

            enigmas.add(new AnswerEnigma(question, answer));
        }

        return enigmas;
    }
}
