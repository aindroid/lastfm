package ainhoamoreno.com.lastfm.data.model.artist.search;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;

import ainhoamoreno.com.lastfm.data.model.artist.Artist;

import static org.junit.Assert.assertEquals;

public class SearchTest {

    private static final String SEARCH_RESPONSE_OK = "{\"results\":{\"opensearch:Query\":{\"#text\":\"\",\"role\":\"request\",\"searchTerms\":\"cher\",\"startPage\":\"1\"},\"opensearch:totalResults\":\"58148\",\"opensearch:startIndex\":\"0\",\"opensearch:itemsPerPage\":\"1\",\"artistmatches\":{\"artist\":[{\"name\":\"Cher\",\"listeners\":\"1032151\",\"mbid\":\"bfcc6d75-a6a5-4bc6-8282-47aec8531818\",\"url\":\"https://www.last.fm/music/Cher\",\"streamable\":\"0\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/660336460ad748babe1915a8cefca481.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/660336460ad748babe1915a8cefca481.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/660336460ad748babe1915a8cefca481.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/660336460ad748babe1915a8cefca481.png\",\"size\":\"extralarge\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/660336460ad748babe1915a8cefca481.png\",\"size\":\"mega\"}]}]},\"@attr\":{\"for\":\"cher\"}}}";

    @Test
    public void getResults() throws Exception {

        // given
        Gson g = new Gson();

        // when
        Search search = g.fromJson(SEARCH_RESPONSE_OK, Search.class);

        // then
        List<Artist> artists = search.getResults().getArtistmatches().getArtist();
        assertEquals(artists.get(0).getLargeImageUrl(),
                "https://lastfm-img2.akamaized.net/i/u/174s/660336460ad748babe1915a8cefca481.png");
    }

}