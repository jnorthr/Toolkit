def fi = new File("/Users/jimnorthrop/Dropbox/MacBookmarks.html")
def fo = new File("/Users/jimnorthrop/Dropbox/MacBookmarks.adoc")
def sb = "";

def text = fi.text
println "file has "+text.size()+" bytes";
 
text.eachLine{ln->
    def i = ln.indexOf(/A HREF=/);
    def h3 = ln.indexOf(/<H3/);

    if (h3 > -1)
    {
        def h3txt = ln.substring(h3)
        def r = h3txt.indexOf(">");
        def h3t = h3txt.substring(r+1);
        //println "h3="+h3t
        r = h3t.indexOf("<");
        def h = h3t.substring(0,r);
        sb+="\n== ${h}\n\n"
    }
    else
    if (i> -1 && h3 < 0)
    {
        def pre = ln.substring(i+8);
        def j = pre.indexOf('"');
        def google = pre.indexOf('script.google')
        if (j> -1 && pre.substring(0,4)=="http" && google<1)
        {
            def href = pre.substring(0,j);

            def k = pre.indexOf('>');
            def suf = pre.substring(k+1)
            def l = suf.indexOf("</A");
            def s = " * "
            s+=href;
            s+="["+suf.substring(0,l)+"]";
            //println s;
            sb+=s;
            sb+="\n";
        } // end ofif    
    } // end of if


} // end of each line


// Or a writer object:
fo.withWriter('UTF-8') { writer ->
    writer.write(sb.toString())
}


println "--- the end ---"