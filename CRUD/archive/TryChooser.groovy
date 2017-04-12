package io.jnorthr.toolkit;

import io.jnorthr.toolkit.Chooser;
import io.jnorthr.toolkit.Response;
import io.jnorthr.toolkit.PathFinder;

public class TryChooser
{
    // =============================================================================    
    /**
     * The primary method to execute this class. Can be used to test and examine logic and performance issues. 
     * 
     * argument is a list of strings provided as command-line parameters. 
     * 
     * @param  args a list of possibly zero entries from the command line
     */
    public static void main(String[] args)
    {

        def ch = new Chooser();

        ch.say "------------------------"
        ch.say "Try a SAVE feature"
        ch.saveAs("save.me");
        ch.setOpenOrSave(false);
        ch.setTitle("Pick a Folder and Filename to save");
        Response re = ch.getChoice(); 
        if (re.abort)
        {
            println "user clicked 'cancel' button"
        } // end of if
        
        println re;

        if (re.chosen && !re.abort)
        {
            ch.say "path="+re.path+"\nartifact name="+re.artifact;    
            ch.say "the full name of the output file is "+re.fullname;
            ch.say "isDir ? = "+re.isDir;    
        }
        else
        {
            ch.say "no choice was made so output path is "+re.path+" and name="+re.fullname;
        }


        println "--- the end ---"
    } // end of main
    
} // end of class
