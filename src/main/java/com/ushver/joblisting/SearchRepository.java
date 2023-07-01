package com.ushver.joblisting;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String search);
}
