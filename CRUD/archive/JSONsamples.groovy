import groovy.json.*
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.json.JsonOutput;
import static groovy.json.JsonParserType.LAX as RELAX

def json = new JsonBuilder()

def root = json.message {
    header {
        from('mrhaki')  // parenthesis are optional
        to 'Groovy Users', 'Java Users'
    }
    body "Check out Groovy's gr8 JSON support."
}
assert root instanceof Map
def js = json.toString()
println js; // {"message":{"header":{"from":"mrhaki","to":["Groovy Users","Java Users"]},"body":"Check out Groovy's gr8 JSON support."}}

// see JsonOutput below; or http://groovy-lang.org/json.html for JsonOutput
def pjs = JsonOutput.prettyPrint(json.toString())
println pjs;             
/*
{
    "message": {
        "header": {
            "from": "mrhaki",
            "to": [
                "Groovy Users",
                "Java Users"
            ]
        },
        "body": "Check out Groovy's gr8 JSON support."
    }
}
*/

pjs = json.getContent(); // creates JSON object: [message:[header:ConsoleScript13$_run_closure1$_closure2@6560243e, body:Check out Groovy's gr8 JSON support.]]
println "JsonBuilder.getContent()="+pjs;

// http://docs.groovy-lang.org/latest/html/gapi/groovy/json/JsonBuilder.html    .call(Map)
def secondTry = json.call(['wife':'Mary','bro':'Alan','mate':'Guillaume','me':'Jim']); // create a root JSON object from Map: [wife:Mary, bro:Alan, mate:Guillaume, me:Jim]
println "secondTry ="+secondTry;          

            def defaultText = '{ "name": "John Doe" } /* some comment */'  // '{ "root" : "here" }'
            def jsonText = new JsonBuilder(defaultText).toPrettyString();
            println "--------\njsonText from Builder:"+jsonText+"\n----------------"
def jslurper = new JsonSlurper().parseText('{ "name": "John Doe" } /* some comment */')            
println "jslurper="+jslurper;  // only on Builders-> .toPrettyString();


println "--- the end ---"

//-----------------------------------
// http://stackoverflow.com/questions/19760138/parsing-json-in-java-without-knowing-json-format
// http://www.journaldev.com/2324/jackson-json-java-parser-api-example-tutorial
	

Take a look at Jacksons built-in tree model feature.

And your code will be:

public void parse(String json)  {
       JsonFactory factory = new JsonFactory();

       ObjectMapper mapper = new ObjectMapper(factory);
       JsonNode rootNode = mapper.readTree(json);  

       Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
       while (fieldsIterator.hasNext()) {

           Map.Entry<String,JsonNode> field = fieldsIterator.next();
           System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
       }
}

