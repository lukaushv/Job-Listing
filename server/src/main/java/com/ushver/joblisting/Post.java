package com.ushver.joblisting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JobPost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String Profile;
    private String desc;
    private int exp;
    private String techs[];
}
