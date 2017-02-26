package com.example.cutit_000.github_user_search.interfaces;

import com.example.cutit_000.github_user_search.Models.Repository;

/**
 * Created by cutit_000 on 23.02.2017.
 */

public interface IRepositoryReceiver {
    void onRepositoryReceived(Repository[] repositories);
}
