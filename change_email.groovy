import hudson.plugins.emailext.*
import hudson.model.*
import hudson.maven.*
import hudson.maven.reporters.*
import hudson.tasks.*

String newRecipients = "\$email_recipient";

println("Recipients: " + newRecipients);
println();

// For each project
Jenkins.instance.items.each { item ->
	println(item.name + ": Checking for email notifiers");
	// Find current recipients defined in project
	if(!(item instanceof ExternalJob) && item.name!='KK_project') {

		for(publisher in item.publishersList) {
			// default Mailer Publisher
			if(publisher instanceof Mailer) {
				println(item.name + " - Updating publisher: " + publisher + " changing recipients from '" + publisher.recipients + "' to '" + newRecipients + "'");
				publisher.recipients = newRecipients;
			}
			// Extended Email Publisher
			else if(publisher instanceof ExtendedEmailPublisher) {
				println(item.name + " - Updating publisher: " + publisher + " changing recipients from '" + publisher.recipientList + "' to '" + newRecipients + "'");
				publisher.recipientList = newRecipients;
			}
		}
	}
}
