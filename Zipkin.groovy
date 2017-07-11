import groovy.json.JsonSlurper

//zipkin helper library 
public class Zipkin {
	String zipkinURL = 'http://zipkin-host:9411/api/v1'
	String [] excludeList = ["service-to-exclude"]

	def getJson (String url){
    	return new JsonSlurper().parseText(new URL(url).text)
	}

	//obtain dependencies of a microservice
	def getDependencies(String service) { 
    	println 'Getting dependencies'
		def json = getJson("$zipkinURL/dependencies/?endTs="+System.currentTimeMillis())
    	def deps = json.findAll { 
        	it.child == service && it.parent!=service && !excludeList.contains(it.parent)
    	}
    
    	def results= new ArrayList()
    		deps.collect { dep ->
        		results.add(dep.parent)
			}
    return results
	}
}