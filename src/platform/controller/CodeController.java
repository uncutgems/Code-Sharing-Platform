package platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.model.SimpleCode;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CodeController {
    private final Map<Integer, Code> codeLib = new HashMap<>();

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public String code(@PathVariable int id) {
        Code myCode = codeLib.get(id);
        return "<html>\n" +
                "<head>\n" +
                "    <title>Code</title>\n" +
                "<span id=\"load_date\">" + myCode.getDate().toString() + "</span>" +
                "</head>\n" +
                "<body>\n" +
                "<pre id=\"code_snippet\">\n" +
                myCode.getCode() +
                "</pre>\n" +
                "</body>\n" +
                "</html>";
    }



    @GetMapping(value = "/api/code/{id}")
    public ResponseEntity<Map<String,String>> getcodebyid(@PathVariable int id) {
        Code myCode = codeLib.get(id);
        System.out.println(myCode.getCode());
        System.out.println(myCode.getDate().toString());
        return ResponseEntity.ok(Map.of("code", myCode.getCode(),
                "date", myCode.getDate().toString()));
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    public ResponseEntity<Map<String,String>> newCode(@RequestBody SimpleCode newcode) {
        System.out.println("Code:" + newcode.getCode());

        Code latest;
        latest = new Code(newcode.getCode());
        codeLib.put(latest.HereIsTheID(), latest);
        return ResponseEntity.ok(Map.of("id", Integer.toString(latest.HereIsTheID())));
    }

    @GetMapping("/code/new")
    public String newCodebyHtml() {
        return "<html>\n" +
                "<head>\n" +
                "<title>Create</title>\n" +
                "<span id=\"load_date\">" + LocalDate.now() + "</span>" +
                "</head>\n" +
                "<body>\n" +
                "<textarea id=\"code_snippet\"> ... </textarea>" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>" +
                "<script>" +
                "function send() {\n" +
                "    let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                "    };\n" +
                "    \n" +
                "    let json = JSON.stringify(object);\n" +
                "    \n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "    \n" +
                "    if (xhr.status == 200) {\n" +
                "      alert(\"Success!\");\n" +
                "    }\n" +
                "}" +
                "</script>" +
                "</pre>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public List<Code> getlatest() {
        codeLib.values().stream()
                .sorted((x, y) -> Integer.compare(y.HereIsTheID(), x.HereIsTheID()))
                .limit(10)
                .sorted(Comparator.comparingInt(Code::HereIsTheID))
                .collect(Collectors.toList())
                .forEach(
                        x -> System.out.println(x.HereIsTheID() + " " + x.getCode()));

        return codeLib.values().stream()
                .sorted((x, y) -> Integer.compare(y.HereIsTheID(), x.HereIsTheID()))
                .limit(10)
                .collect(Collectors.toList());

    }

    @GetMapping("/code/latest")
    public String getlatesthtml(Model model) {
        List<Code> firstten =
                codeLib.values().stream()
                        .sorted((x, y) -> Integer.compare(y.HereIsTheID(), x.HereIsTheID()))
                        .limit(10).collect(Collectors.toList());
        String mylist = "";
        for (Code code : firstten) {
            mylist = mylist + "<span>" + code.getDate().toString() + "</span>\n" +
                    "<pre>" + code.getCode() + "</pre>\n";
        }

        return "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Latest</title>\n" +
                "</head>\n" +
                "<body>\n" +
                mylist +
                "</body>\n" +
                "</html>";
/*
        model.addAttribute("codes", firstten);
        return "latest";*/
    }
}
