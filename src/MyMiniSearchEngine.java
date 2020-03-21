import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    //
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
        String[] words= keyPhrase.split(" ");
        words[0] = strip(words[0]);
        List<List<Integer>> result = indexes.get(words[0]);     // Finds all values of key words[0]
        for(int i = 1; i < words.length; i++) {
            words[i] = strip(words[i]);
            List<List<Integer>> temp = indexes.get(words[i]);   //This is the values of all the next word.
            if(temp == null) {
                return new ArrayList<>();
            }
            for (int j = 0; j < result.size(); j++) {
                if (result.get(j).isEmpty()) {
                    temp.get(j).clear();
                } else {
                    for (int n = 0; n < temp.get(j).size(); n++) {
                        int r = 0;
                        while (result.get(j).get(r) < temp.get(j).get(n)) {
                            r++;
                            if(r>=result.get(j).size())
                                break;
                        }
                        if(r > 0) --r;
                        if (result.get(j).get(r) != (temp.get(j).get(n) - 1)) {
                            temp.get(j).remove(n);
                            n--;
                        }
                    }
                }
            }
            result = temp;

        }
        List<Integer> answer = new ArrayList<>();
        if(result==null) return answer;
        for(int i = 0; i < result.size(); i++){
            if(!result.get(i).isEmpty()){
                answer.add(i);
            }
        }

        return answer;

    }
    public static String strip (String string){
        string = string.toLowerCase();
        string = string.replaceAll("[^a-z]", "");
        return string;
    }
}
