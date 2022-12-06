package y2022;

import util.AOCDay;
import util.AOCYear;
import y2022.d1.D1;
import y2022.d2.D2;
import y2022.d3.D3;
import y2022.d4.D4;

import java.util.List;

public class Y2022 extends AOCYear {

    @Override
    public List<AOCDay> getDays() {
        return List.of(
                new D1(),
                new D2(),
                new D3(),
                new D4()
        );
    }

    @Override
    public int getYear() {
        return 2022;
    }
}
