import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    private Map<String, List<List<Integer>>> indexes;

    // disable default constructor

    private MyMiniSearchEngine()
    {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
        indexes=new HashMap<>();
        for(int i=0;i<texts.size();i++)
        {
            String[] arr= texts.get(i).split(" ");
            //every individual word
            for(int j=0;j<arr.length;j++)
            {
                List<List<Integer>> outer=new ArrayList();
                for (int k = 0; k < texts.size(); k++) {
                    outer.add(new ArrayList<Integer>());
                }
                if(!indexes.containsKey(arr[j]))
                {
                    indexes.put(arr[j],outer);
                }
                indexes.get(arr[j]).get(i).add(j);
            }
        }
        System.out.println(indexes.toString());
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        for(int i=0;i<indexes.size();i++){
            if(indexes.get(i).contains(keyPhrase)))
            {
                return indexes.get(i);
            }
        }
        return new ArrayList<>(); // place holder
    }
}
