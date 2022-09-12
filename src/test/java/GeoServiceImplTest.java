import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static ru.netology.geo.GeoServiceImpl.*;

public class GeoServiceImplTest {
    @ParameterizedTest
    @MethodSource("source")
    public void localeTest(String ip, Location expected){

        GeoService geoService=new GeoServiceImpl();
        Location result = geoService.byIp(ip);
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("127.0.0.1",       new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11",     new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149",   new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.",            new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.",             new Location("New York", Country.USA, null,  0)),
                Arguments.of("130.",            null)
        );
    }

    @Test
    public void byCoordinatesTest(){

        GeoService geoService=new GeoServiceImpl();
        Class<RuntimeException> expected=RuntimeException.class;
        Assertions.assertThrows(expected,()->geoService.byCoordinates(0.0,0.0));
    }
}