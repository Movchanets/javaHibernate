package models;

import com.sun.xml.bind.v2.runtime.Name;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_questions")
public class Question {

    public Question()
    {
        answers = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 500, nullable = false)
    private  String name;
    @OneToMany(mappedBy = "question",cascade=CascadeType.REMOVE)
    private List<Answer> answers;
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(this.name +'\n');
        int i = 0;
        for (Answer a:answers ) {
            str.append(++i +"." +a.getText()+'\t');
        }
        return str.toString();
    }
}
