package week3;

import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HWday10 {
    // using treemap with comparator store the data
    public static void main(String[] args) {
        cacheIsWorking();
    }
    @Test
    static void cacheIsWorking() {

        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);

        assertEquals(4, cache.getPlaysForSong("ID-1"));
        assertEquals(-1, cache.getPlaysForSong("ID-9"));

//        assertThat(cache.getTopNSongsPlayed(2), contains("ID-3", "ID-1"));
//        List<String> comp = new ArrayList<>();
//        assertArrayEquals(comp, cache.getTopNSongsPlayed(2));
        System.out.println(cache.getTopNSongsPlayed(3));
        assertNull(cache.getTopNSongsPlayed(0));
    }
}

interface SongCache {
    /**
     * Record number of plays for a song.
     */
    void recordSongPlays(String songId, int numPlays);

    /**
     * Fetch the number of plays for a song.
     *
     * @return the number of plays, or -1 if the
    song ID is unknown.
     */
    int getPlaysForSong(String songId);

    /**
     * Return the top N songs played, in descending
     order of number of plays.
     */
    List<String> getTopNSongsPlayed(int n);
}

class SongCacheImpl implements SongCache{
    //synchronized Hashtable
    private volatile Hashtable<String, Integer> myCache = new Hashtable<>();
    private volatile PriorityQueue<String> frequerncy = new PriorityQueue<>((a,b)-> {
        Integer v1 = myCache.get(a);
        Integer v2 = myCache.get(b);
        return v2 - v1;
    });


    public void recordSongPlays(String songId, int numPlays){
        if(!myCache.containsKey(songId)){
            myCache.put(songId, numPlays);
            frequerncy.offer(songId);
        }
        else{
            int pre=myCache.get(songId);
            myCache.replace(songId, numPlays+pre);
            frequerncy.remove(songId);
            frequerncy.offer(songId);
        }
    }

    public int getPlaysForSong(String songId){
        if(myCache.containsKey(songId)){
            return myCache.get(songId);
        }
        else {
            return -1;
        }
    }

    public List<String> getTopNSongsPlayed(int n){
        if(frequerncy.isEmpty() || n<=0) return null;
        List<String> ans = new ArrayList<>();
        for(int i=0;i<(frequerncy.size()>=n?n:frequerncy.size());i++){
            ans.add(frequerncy.poll());
        }
        return ans;
    }

}