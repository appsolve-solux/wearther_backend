package com.appsolve.wearther_backend.closet;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingUrls {
    public static final Map<Long, List<Pair<String, String>>> UPPER_WEAR_INFO = new HashMap<>() {{

        put(1L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%AF%BC%EC%86%8C%EB%A7%A4", "지그재그"), // 민소매
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMjF9LCAiZCI6ICJDQVRFR09SWSIsICJwcmV2aW91c19zY3JlZW5fbmFtZSI6ICJDQVRFR09SWV9ERVBBUlRNRU5UIiwgImNhdGVnb3J5X3NubyI6IDIxfQ%3D%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001011?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%AF%BC%EC%86%8C%EB%A7%A4&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(2L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=474&sub_category_id=2791", "지그재그"), // 반소매
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMjF9LCAiZCI6ICJDQVRFR09SWSIsICJwcmV2aW91c19zY3JlZW5fbmFtZSI6ICJDQVRFR09SWV9ERVBBUlRNRU5UIiwgImNhdGVnb3J5X3NubyI6IDIxfQ%3D%3D&filter_sub_category_sno=18", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001001?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%B0%98%EC%86%8C%EB%A7%A4&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(3L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8%20%ED%9B%84%EB%93%9C%ED%8B%B0", "지그재그"), // 맨투맨, 후드티
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8%20%ED%9B%84%EB%93%9C%ED%8B%B0&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8+%ED%9B%84%EB%93%9C&gf=A&isOriginal=true", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8+%ED%9B%84%EB%93%9C&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(4L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%85%94%EC%B8%A0%20%EB%B8%94%EB%9D%BC%EC%9A%B0%EC%8A%A4", "지그재그"), // 셔츠, 블라우스
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%85%94%EC%B8%A0%20%EB%B8%94%EB%9D%BC%EC%9A%B0%EC%8A%A4&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001002?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%85%94%EC%B8%A0+%EB%B8%94%EB%9D%BC%EC%9A%B0%EC%8A%A4&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(5L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=474&sub_category_id=482", "지그재그"), // 니트
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMjk5fSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiQ0FURUdPUllfREVQQVJUTUVOVCIsICJjYXRlZ29yeV9zbm8iOiAyOTl9", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001006?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%8B%88%ED%8A%B8&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&keywordTypes=category", "29cm")));

        put(6L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94", "지그재그"), // 오프숄더
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=", "29cm")));

        put(7L, List.of(Pair.of("https://zigzag.kr/search?keyword=%ED%9E%88%ED%8A%B8%ED%83%9D", "지그재그"), // 히트택
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%9E%88%ED%8A%B8%ED%83%9D&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%ED%9E%88%ED%8A%B8%ED%83%9D&keywordType=keyword&gf=A", "무신사")));

        put(8L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EA%B8%B0%EB%AA%A8", "지그재그"), // 기모
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EA%B8%B0%EB%AA%A8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EA%B8%B0%EB%AA%A8&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EA%B8%B0%EB%AA%A8&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=", "29cm")));

        put(9L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=437", "지그재그"), // 가디건
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMTZ9LCAiZCI6ICJDQVRFR09SWSIsICJwcmV2aW91c19zY3JlZW5fbmFtZSI6ICJPVkVSVklFVyIsICJjYXRlZ29yeV9zbm8iOiAxNn0%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002020?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EA%B0%80%EB%94%94%EA%B1%B4&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(10L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=460", "지그재그"), // 사파리자켓
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%82%AC%ED%8C%8C%EB%A6%AC%EC%9E%90%EC%BC%93&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002014?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%82%AC%ED%8C%8C%EB%A6%AC%EC%9E%90%EC%BC%93&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&keywordTypes=category", "29cm")));

        put(11L, List.of(Pair.of("https://zigzag.kr/search?keyword=%ED%8A%B8%EC%9C%84%EB%93%9C%EC%9E%90%EC%BC%93", "지그재그"), // 트위드 자켓
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%8A%B8%EC%9C%84%EB%93%9C%EC%9E%90%EC%BC%93&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%ED%8A%B8%EC%9C%84%EB%93%9C+%EC%9E%90%EC%BC%93&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%ED%8A%B8%EC%9C%84%EB%93%9C%EC%9E%90%EC%BC%93&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=", "29cm")));

        put(12L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=442", "지그재그"), // 레더 자켓
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%A0%88%EB%8D%94%EC%9E%90%EC%BC%93&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002002?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%A0%88%EB%8D%94%EC%9E%90%EC%BC%93&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(13L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=447", "지그재그"), // 트렌치코트
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%8A%B8%EB%A0%8C%EC%B9%98%EC%BD%94%ED%8A%B8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002?keyword=%ED%8A%B8%EB%A0%8C%EC%B9%98%EC%BD%94%ED%8A%B8&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%ED%8A%B8%EB%A0%8C%EC%B9%98%EC%BD%94%ED%8A%B8&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&page=1", "29cm")));

        put(14L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%88%8F%EC%BD%94%ED%8A%B8", "지그재그"), // 숏코트
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%88%8F%EC%BD%94%ED%8A%B8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002?keyword=%EC%88%8F%EC%BD%94%ED%8A%B8&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%88%8F%EC%BD%94%ED%8A%B8&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&page=1", "29cm")));

        put(15L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%AC%B4%EC%8A%A4%ED%83%95", "지그재그"), // 무스탕
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%AC%B4%EC%8A%A4%ED%83%95&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002025?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%AC%B4%EC%8A%A4%ED%83%95&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(16L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EA%B2%BD%EB%9F%89%20%ED%8C%A8%EB%94%A9", "지그재그"), // 경량 패딩
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EA%B2%BD%EB%9F%89%20%ED%8C%A8%EB%94%A9&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EA%B2%BD%EB%9F%89+%ED%8C%A8%EB%94%A9&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EA%B2%BD%EB%9F%89+%ED%8C%A8%EB%94%A9&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=brand", "29cm")));

        put(17L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=464", "지그재그"), // 롱패딩
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%A1%B1%ED%8C%A8%EB%94%A9&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%A1%B1%ED%8C%A8%EB%94%A9&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%A1%B1%ED%8C%A8%EB%94%A9&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(18L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=463", "지그재그"), // 숏패딩
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%88%8F%ED%8C%A8%EB%94%A9&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%88%8F%ED%8C%A8%EB%94%A9&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%88%8F%ED%8C%A8%EB%94%A9&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(19L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=454", "지그재그"), // 점퍼
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%A0%90%ED%8D%BC&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%A0%90%ED%8D%BC&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%A0%90%ED%8D%BC&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(20L, List.of(Pair.of("https://zigzag.kr/search?keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85", "지그재그"), // 후드집업
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&keywordTypes=category", "29cm")));

        put(21L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4", "지그재그"), // 바람막이
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));
    }};

    public static final Map<Long, List<Pair<String, String>>> LOWER_WEAR_INFO = new HashMap<>() {{

    }};

    public static final Map<Long, List<Pair<String, String>>> OTHER_WEAR_INFO = new HashMap<>() {{

    }};
}