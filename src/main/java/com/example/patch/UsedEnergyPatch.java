package com.example.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.example.helpers.StaticHelper;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

@SpirePatch(clz = EnergyPanel.class, method = "useEnergy")
public class UsedEnergyPatch {
  public static void Prefix(int e) {
    StaticHelper.usedEnergy += Math.min(EnergyPanel.totalCount, e);
  }
}
