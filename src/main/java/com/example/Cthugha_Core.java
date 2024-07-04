package com.example;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.example.cards.*;
import com.example.characters.Cthugha;
import com.example.enums.*;
import com.example.helpers.StaticHelper;
import com.example.relics.HuoTiHuoYan;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.OnStartBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

import static com.megacrit.cardcrawl.core.Settings.language;
import com.google.gson.Gson;

@SpireInitializer
public class Cthugha_Core
        implements EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, OnStartBattleSubscriber  {

    public static final Logger logger = LogManager.getLogger(Cthugha_Core.class.getName());

    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "cthughaResources/img/renwu.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "cthughaResources/img/anime-fandoms-Black-Bullet-1620248_2.png";

    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "cthughaResources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "cthughaResources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "cthughaResources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "cthughaResources/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "cthughaResources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "cthughaResources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "cthughaResources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "cthughaResources/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "cthughaResources/img/char/cost_orb.png";

    // 除以255得出需要的参数。你也可以直接写出计算值。
    public static final Color BLOOD_COLOR = new Color(249.0F / 255.0F, 1.0F / 255.0F, 5.0F / 255.0F, 1.0F);

    // 构造方法
    public Cthugha_Core() {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.MOD_NAME_COLOR, BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR,
                BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB,
                BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);

    }

    // 注解需要调用的方法，必须写
    public static void initialize() {
        new Cthugha_Core();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Cthugha("123"), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT,
                MyPlayerClassEnum.MY_PLAYER_CLASS);
    }

    @Override
    public void receiveEditCards() {
        // BaseMod.addCard(new CiJi());
        // BaseMod.addCard(new XiaDunHuiJian());

        new AutoAdd("Cthugha")
                .packageFilter("com.example.cards")
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new HuoTiHuoYan(), AbstractCardEnum.MOD_NAME_COLOR);
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "cthughaResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "cthughaResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, "cthughaResources/localization/" + lang + "/orbs.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "cthughaResources/localization/" + lang + "/powers.json");

        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"

        // 这里添加注册本地化文本
        // BaseMod.loadCustomStringsFile(CharacterStrings.class,
        // "cthughaResources/localization/" + lang + "/characters.json");
        // BaseMod.loadCustomStringsFile(PowerStrings.class,
        // "cthughaResources/localization/" + lang + "/powers.json");
        // BaseMod.loadCustomStringsFile(EventStrings.class,
        // "cthughaResources/localization/" + lang + "/events.json");

    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "eng";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
        }

        String json = Gdx.files.internal("cthughaResources/localization/ZHS/keywords_" + lang + ".json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("cthugha:", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom room) {
        StaticHelper.resetvaluesAtBattleStart();
    }

}
