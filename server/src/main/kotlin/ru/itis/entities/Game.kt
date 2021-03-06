package ru.itis.entities

import org.json.JSONObject

class Game(var clients: ArrayList<Connection>) {
    private var step = GameStep.PREFLOP
    private var common = Array<Card?>(5) {i -> null}

    /**
     * В покер играют от 2 до 10 человек.
     * Ходят по очереди и в игре существуют несколько этапов, так называемых улиц – это Префлоп, Флоп, Торн, Ривер.
     */
    fun run() {
        preflop()
        flop()
        torn()
        river()
        shown_down()
    }

    /**
     * @step Preflop
     * Первое с чего начинается игра, это 2 человека после Дилера (значок D)
     * ставят обязательные ставки Малый Блайнд и Большой Блайнд.
     * Из них и формируется первоначальный банк.
     *
     * Далее раздают всем игрокам по 2 закрытые карты и настает очередь ходить другим.
     * Игроки ходят по часовой стрелке начиная со следующего после поставившего большой блайнд игрока.
     * Две розданные карты хоть и дают пока мало информации, но именно опираясь на неё,
     * а так же на свою позицию за столом и действия других игроков нужно будет сделать ход.
     *
     * У игрока 3 варианта действии:
     * 1. Фолд – Сброс карты. Если у вас слабые карты (например, 72), то это практический единственное что вы должны сделать. Если вы сбросили карты, то уже выходите из игры и не платите никаких денег.
     * 2. Колл – Поддерживаете ранее сделанную другими ставку. Допустим у вас средние карты, зашло много игроков, нет рейзов. Вы ставите ровно столько, сколько поставили другие, в нашем случае 10 фишек.
     * 3. Увеличить / Рейз – Вы повышаете ставки. Допустим у вас хорошие карты и вы хотите чтобы соперники вам заплатили побольше. Возможно вам никто не ответит и вы прямо сейчас заберёте этот банк. В зависимости от варианта Холдема повышать можно от 1 блайнда до алл-ина.
     */
    fun preflop() {
        step = GameStep.PREFLOP
        /**
         * forEach(blinds)
         * forEach(getAction)
         */
    }

    /**
     * @step Flop
     * После того как закончился круг торговли на префлопе, все сделали ставки или выкинули карты, начинается раздача первых 3-х общих карт из 5.
     * После раздачи флопа более менее становиться понятной ситуация в раздаче. Наш игрок сидит на позиции дилера (D), а дилер всегда ходит последним. В данной раздаче мы видим, что у нашего игрока почти образовался флеш с бубями. Все игроки до него сделали чек и теперь его ход. Он может сыграть фолд / чек / сделать ставку:
     * 1. Фолд – Сброс карты. В данном случае в этом просто нет необходимости. Во первых намечается хорошая комбинация, во-вторых еще никто не сделал ставку, поэтому следующую карту в любом случае можно посмотреть бесплатно.
     * 2. Чек – т.е. не предпринять действий, просто спасовать.
     * 3. Сделать ставку / Рейз – Еще никто не сделал ставку, поэтому это можете сделать вы. Если вы сделаете ставку, то начнётся новый раунд торговли и другие игроки будут вынуждены тоже сделать ставки или выкинуть карты. Вполне возможно также получить в ответ рейз, ели кто-то решил сыграть чек-рейз. Размер ставки ограничен только вашим стеком, например, в данном случае можно ставить от 10 до алл-ин (479,70 фишек).
     */
    fun flop() {
        step = GameStep.FLOP
        /**
         * show() [3]
         * forEach(getAction)
         */
    }

    /**
     * @step Torn
     * После флопа наступает следующий этап игры – торн. Здесь раздается предпоследняя 4 карта в раздаче.
     * На торне мы по прежнему еще не имеем никакой покерной комбинации и игрок из средней позиции сделал ставку в 10 фишек.
     * По правилам игры в покер, теперь мы не сможем спасовать как на флопе,
     * и нам придётся играть Фолд / Колл / Увеличить ставку.
     * В банке уже много фишек, а у нас хорошие шансы на флеш, поэтому фолд отпадает.
     * Увеличить ставку тоже не вариант, хотя у нас и может собраться флеш, но он далеко не старший,
     * поэтому в случае если он выпадет придётся очень осторожно его разыгрывать. Остается просто поддержать ставку.
     */
    fun torn() {
        step = GameStep.TORN
        /**
         * show()
         * forEach(getAction)
         */
    }

    /**
     * @step River
     * Наступает момент истины. К концу игры на ривере определится победитель раздачи.
     * Выпадает удачная карта для нас и наш игрок собирает свой флеш.
     * Игрок перед нами делает ставку в 1/2 банка.
     * Нам отвечать. Как всегда у нас несколько вариантов для ответа: Фолд, Колл или Рейз.
     * С готовым флешем напрашивается повышение рейзом, но смущает то, что у нас не натс-флеш и спаренная доска
     * (возможность фулл-хауса), поэтому просто колл.
     */
    fun river() {
        step = GameStep.RIVER
        /**
         * show()
         * forEach(getAction)
         */
    }

    /**
     * @step Shown Down
     * Вскрытие карт является заключительным этапом в Техасском Холдеме и выявляет победителя (лей),
     * который и заберёт банк.
     * В нашем случае им оказался соперник из Австралии, который показал старший флеш и забрал весь банк.
     */
    fun shown_down() {
        step = GameStep.SHOWN_DOWN
        /**
         * show
         * calc
         * reissue cards
         */
    }



    fun dispatch(client: Connection, request: JSONObject) {

    }
}