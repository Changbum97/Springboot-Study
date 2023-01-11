package com.study.hellospring.json_example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/json-example")
public class JsonController {

    @Data
    @AllArgsConstructor
    static class Player {
        private String name;
        private int backNumber;
        private int age;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class FootballTeam {
        private String teamName;
        private String league;
        private Integer ranking;
        private ArrayList<Player> players;
    }

    @GetMapping("/player")
    public Player getOnePlayer() {
        Player player1 = new Player("Son Heungmin", 7, 30);
        return player1;
    }

    @GetMapping("/players")
    public ArrayList<Player> getAllPlayer() {
        Player player1 = new Player("Son Heungmin", 7, 30);
        Player player2 = new Player("Harry Kane", 10, 29);
        Player player3 = new Player("Hugo Lloris", 1, 36);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        return players;
    }

    @GetMapping("/team")
    public FootballTeam getOneTeam() {
        Player player1 = new Player("Son Heungmin", 7, 30);
        Player player2 = new Player("Harry Kane", 10, 29);
        Player player3 = new Player("Hugo Lloris", 1, 36);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        FootballTeam footballTeam = new FootballTeam("Tottenham", "Premier League", 5, players);
        return footballTeam;
    }

    @PostMapping("/player")
    public String postOnePlayer(@RequestBody Player player) {
        String response = String.format("<선수 정보>\n이름 : %s\n등번호 : %d\n나이 : %d",
                player.getName(), player.getBackNumber(), player.getAge());
        return response;
    }

    @PostMapping("/players")
    public String postPlayers(@RequestBody List<Player> players) {
        String response = "<선수단 정보>\n";

        int i = 1;
        for (Player player : players) {
            response += String.format("선수%d: 이름 : %s\t등번호 : %d\t나이 : %d\n",
                    i, player.getName(), player.getBackNumber(), player.getAge());
            i ++;
        }

        return response;
    }

    @PostMapping("/team")
    public String postOneTeam(@RequestBody FootballTeam footballTeam) {
        String response = String.format("<팀 정보>\n팀명 : %s\n리그 : %s\n리그 랭킹 : %d\n",
                footballTeam.getTeamName(), footballTeam.getLeague(), footballTeam.getRanking());

        int i = 1;
        for (Player player : footballTeam.getPlayers()) {
            response += String.format("선수%d: 이름 : %s\t등번호 : %d\t나이 : %d\n",
                    i, player.getName(), player.getBackNumber(), player.getAge());
            i ++;
        }

        return response;
    }

    @PostMapping("/json-parser/string-to-player")
    public Player convertStringToPlayer1(@RequestBody String jsonStr) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr);

        Player player = new Player(jsonObject.get("name").toString(),
                Integer.valueOf(jsonObject.get("backNumber").toString()),
                Integer.valueOf(jsonObject.get("age").toString()));
        return player;
    }

    @PostMapping("/json-parser/player-to-string")
    public String convertPlayerToString1(@RequestBody Player player) {
        JSONObject jsonPlayer = new JSONObject();
        jsonPlayer.put("name", player.getName());
        jsonPlayer.put("backNumber", player.getBackNumber());
        jsonPlayer.put("age", player.getAge());

        String jsonStr = jsonPlayer.toJSONString();
        return jsonStr;
    }

    @PostMapping("/gson/string-to-player")
    public Player convertStringToPlayer2(@RequestBody String jsonStr) {
        Gson gson = new Gson();
        Player player = gson.fromJson(jsonStr, Player.class);
        return player;
    }

    @PostMapping("/gson/string-to-team")
    public FootballTeam convertStringToTeam2(@RequestBody String jsonStr) {
        Gson gson = new Gson();
        FootballTeam footballTeam = gson.fromJson(jsonStr, FootballTeam.class);
        return footballTeam;
    }

    @PostMapping("/gson/team-to-string")
    public String convertTeamToString2(@RequestBody FootballTeam footballTeam) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(footballTeam);
        return jsonStr;
    }


    @PostMapping("/object-mapper/string-to-team")
    public FootballTeam convertStringToTeam3(@RequestBody String jsonStr) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> footballTeamMap = objectMapper.readValue(jsonStr, new TypeReference<>(){});
        FootballTeam footballTeam = new FootballTeam(
                footballTeamMap.get("teamName").toString(),
                footballTeamMap.get("league").toString(),
                Integer.valueOf(footballTeamMap.get("ranking").toString()),
                (ArrayList<Player>) footballTeamMap.get("players"));

        return footballTeam;
    }

    @PostMapping("/object-mapper/team-to-string")
    public String convertTeamToString3(@RequestBody FootballTeam footballTeam) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonStr = objectMapper.writeValueAsString(footballTeam);
        return jsonStr;
    }
}
