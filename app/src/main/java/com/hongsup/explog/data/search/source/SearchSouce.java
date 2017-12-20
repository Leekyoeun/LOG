package com.hongsup.explog.data.search.source;

import com.hongsup.explog.data.post.PostCover;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

/**
 * Created by Hong on 2017-12-21.
 */

public interface SearchSouce {

    interface Local{
        List<String> loadRecentSearchWord();

        void insertSearchWord(String word);
        void deleteSearchWord(String word);
    }

    interface Remote{

        Observable<Response<List<PostCover>>> loadSearchResult(String word);

    }
}
