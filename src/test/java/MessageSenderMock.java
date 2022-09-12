import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
//import static ru.netology.entity.Location;

public class MessageSenderMock {
    @Test
    void test_send_RU(){
        GeoService geoService= Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.1.1.1"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));


        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender=new MessageSenderImpl(geoService,localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.1.1.1");
        String preferences=messageSender.send(headers);
        String expected="Добро пожаловать";
        Assertions.assertEquals(expected, preferences);
    }
}
