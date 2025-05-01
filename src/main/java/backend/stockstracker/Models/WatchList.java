package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;

import java.util.List;
import java.util.Map;

@Entity
public class WatchList extends BaseModel {

    private String name;
    private String description;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "watchList")
    private List<StckWatchList> stckWatchList;

}
