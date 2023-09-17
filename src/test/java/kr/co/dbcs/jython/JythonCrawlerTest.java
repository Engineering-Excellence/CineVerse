package kr.co.dbcs.jython;

import kr.co.dbcs.config.RootConfig;
import kr.co.dbcs.config.SecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class JythonCrawlerTest {

    @Test
    void testJythonCrawler() {
        log.info("testJythonCrawler()");

        try (PythonInterpreter py = new PythonInterpreter()) {
            String pythonCode =
                    "import json\n" +
//                            "import pprint\n" +
                            "from datetime import datetime\n" +
                            "from urllib import urlencode\n" +
                            "from urllib2 import Request, urlopen\n" +
                            "\n" +
                            "url = \"https://www.lottecinema.co.kr/LCWS/Ticketing/TicketingData.aspx?\"\n" +
                            "now = datetime.now()\n" +
                            "today = str('%s-%s-%s' % (now.year, now.month, now.day))\n" +
                            "movie_list = []\n" +
                            "area_list = [\"1013\", \"1018\", \"9010\", \"1004\", \"1009\",\n" +
                            "             \"1003\", \"1017\", \"9056\", \"1012\", \"1019\",\n" +
                            "             \"1022\", \"1015\", \"1007\", \"1001\", \"1002\",\n" +
                            "             \"1014\", \"1016\",\"1021\",\"9053\",\"1008\",\n" +
                            "             '1010', '1005', '1011']\n" +
                            "\n" +
                            "for area_code in area_list:\n" +
                            "    dic = {\n" +
                            "        'MethodName': 'GetPlaySequence',\n" +
                            "        'channelType': 'HO',\n" +
                            "        'osType': 'Chrome',\n" +
                            "        'osVersion':\n" +
                            "            '''Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 \n" +
                            "            (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36''',\n" +
                            "        'playDate': today,\n" +
                            "        'cinemaID': '%s|%s|%s' % ('1', '1', area_code),\n" +
                            "        'representationMovieCode': ''\n" +
                            "    }\n" +
                            "\n" +
                            "    data = urlencode({'paramList': str(dic)})\n" +
                            "    req = Request(url, data=data)\n" +
                            "    response=urlopen(req)\n" +
                            "\n" +
                            "    result_string=response.read().decode('utf-8')\n" +
                            "    result=json.loads(result_string)\n" +
                            "\n" +
                            "    for i in result['PlaySeqs']['Items']:\n" +
                            "        movie_dic = {\n" +
                            "            \"name\":i.get('MovieNameKR'),\"dt_area\":i.get('ScreenNameKR'),\n" +
                            "                                                 \"start_time\":i.get('StartTime'),\"seat\":i.get('BookingSeatCount'),\n" +
                            "                                                                                       \"area\":i.get('CinemaNameKR'),\"date\":today\n" +
                            "        }\n" +
                            "        movie_list.append(movie_dic)\n" +
//                            "pprint.pprint(movie_list)";
//                            "print(movie_list)";
                            "for movie in movie_list:\n" +
                            "    print('name: {0}, dt_area: {1}, start_time: {2}, seat: {3}, area: {4}, date: {5}'.format(\n" +
                            "        movie['name'].encode('utf-8'), \n" +
                            "        movie['dt_area'].encode('utf-8'), \n" +
                            "        movie['start_time'], \n" +
                            "        movie['seat'], \n" +
                            "        movie['area'].encode('utf-8'), \n" +
                            "        movie['date']\n" +
                            "    ))";

            py.exec(pythonCode);
        }
    }
}
