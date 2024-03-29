<h1 align="center" id="title">Маркетплейс</h1>

<p id="description">
  Этот репозиторий содержит приложение онлайн маркетплейса, разработанное в рамках технического задания на собеседование. Код написан на языке Kotlin. 
  Приложение представляет собой платформу для покупок, которая включает следуюшие экраны: авторизация пользователя, каталог товаров с возможностями фильтрации через Chip и 
  сортировки с Spinner, карточку с подробным описанием товара, личный кабинет пользователя и список избранных товаров пользователя.
</p>

<img src="https://gdurl.com/w1Jp" alt="project-screenshot" width="200" height="400/"> <img src="https://gdurl.com/dKTl" alt="project-screenshot" width="200" height="400/"> <img src="https://gdurl.com/tic4" alt="project-screenshot" width="200" height="400/"> <img src="https://gdurl.com/aW8y" alt="project-screenshot" width="200" height="400/"> <img src="https://gdurl.com/wVar" alt="project-screenshot" width="200" height="400/">

<h2>Экран авторизации пользователя</h2>

<table>
  <tr>
    <td width="400" height="650">
      <img src="https://gdurl.com/TjYD" width="250" height="520" />
    </td>
    <td width="800">
      <p>
        Экран авторизации предлагает пользователям авторизацию через заполнение трех полей: имя и фамилия с валидацией на кириллицу, любой невалидный символ, включая пробелы, знаки пунктуации и латинские буквы, вызывает появление красной подсветки поля, сигнализируя о необходимости коррекции. И поле для номера телефона с вводом по маске "+7 ХХХ ХХХ-ХХ-ХХ". В полях имени и фамилии действует строгая валидация, при ошибке поля подсвечиваются красным, а ввод некорректных символов блокируется. </p>
    <p> Все поля предусматривают кнопку для быстрого удаления введенного текста. Вход в приложение разрешен только после заполнения всех полей допустимыми значениями, активируя кнопку "Войти". Все введенные данные сохраняются локально в базу данных Room.
      </p>
    </td>
  </tr>
</table>

<h2>Экран каталога товаров</h2>

<p>
<table>
  <tr>
    <td width="400" height="650">
      <img src="https://gdurl.com/qTXd" width="250" height="520" />
    </td>
    <td width="800">
      <p>
        Экран "Каталог" предлагает сортировку товаров по популярности и цене (возрастание или уменьшение), карусель чипов для фильтрации товаров по различным категориям, с подсветкой активного тэга. Товары отображаются в двух колонках, с возможностью перехода на страницу описания товара при клике на карточку. Каждая карточка товара включает свайп-картинки, иконку добавления товара в категорию "Избранное", информацию о цене (старая и новая с процентом скидки), рейтинг товара и количество отзывов.
      </p>
    </td>
  </tr>
</table>
</p>

<h2>Экран с описанием товара</h2>

<table>
  <tr>
    <td width="400" height="650">
      <img src="https://gdurl.com/Wtp6" width="250" height="520" />
    </td>
    <td width="800">
      <p>
        Экран "Страница товара" предлагает детальный просмотр с возможностью добавления в избранное, отображая товар с полной информацией: заголовок, подзаголовок, наличия остатков (с динамическим склонением слова "штука" в зависимости от количества) , рейтинг и отзывы (склонение слова "отзыв" аналогино слову "штука"). Пользователи видят старую и новую цены, процент скидки, а также детальное описание и характеристики товара. TextView cостава и описание товара доступны с опцией "Подробнее"/"Скрыть" для экономии пространства. 
      </p>
    </td>
  </tr>
</table>

<h2>Экран личного кабинета и избранных товаров </h2>

<table>
  <tr>
    <td width="400" height="650">
      <img src="https://gdurl.com/LXtn" width="250" height="520" />
    </td>
    <td width="800">
      <p>
        Личный кабинет представляет собой раздел, где отображаются имя, фамилия и номер телефона пользователя, указанные при регистрации. Оснащен иконкой выхода и кнопкой "Избранное", которая направляет на экран с избранными товарами и показывает их количество. Если избранных товаров нет, текст с количеством не отображается. Слово "товар" склоняется в соответствии с числом товаров.
        </p>

<p>
        Экран "Избранное" доступен из личного кабинета и включает иконку "назад" для возвращения на предыдущий экран. Представляет две вкладки TabLayout вместе с ViewPager2: "Товары" и "Бренды". По умолчанию активна вкладка "Товары", на которой показаны избранные товары с возможностью удаления из избранного по клику на соответствующую иконку. Вкладка "Бренды" отображается без содержимого. Отображение товаров на вкладке "Товары" повторяет логику экрана "Каталог".
      </p>
    </td>
  </tr>
</table>

<h2>💻 Используемый стек технологий:</h2>

*   Jetpack Navigation
*   MVVM
*   Recycler View
*   Room(SQLite)
*   Retrofit
*   Coroutine
*   Flow
*   Multimodular
*   Dagger 2+ Hilt
