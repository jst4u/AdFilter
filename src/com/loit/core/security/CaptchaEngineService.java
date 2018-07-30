package com.loit.core.security;


import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

public class CaptchaEngineService extends ListImageCaptchaEngine
{
  protected void buildInitialFactories()
  {
    int m = 4;
    int l = 4;
    int k = 45;
    int j = 180;
    int i = 70;

    WordGenerator h = new ComposeDictionaryWordGenerator(
      new FileDictionary("toddlist"));

    TextPaster g = new DecoratedRandomTextPaster(Integer.valueOf(m), 
      Integer.valueOf(l), 
      new RandomListColorGenerator(new Color[] { 
      new Color(23, 170, 27), new Color(220, 34, 11), 
      new Color(23, 67, 172) }), new TextDecorator[0]);
    BackgroundGenerator f = new UniColorBackgroundGenerator(
      Integer.valueOf(j), Integer.valueOf(i), Color.white);
    FontGenerator e = new RandomFontGenerator(Integer.valueOf(k), Integer.valueOf(k), 
      new Font[] { new Font("nyala", 1, k), 
      new Font("Bell MT", 0, k), 
      new Font("Credit valley", 1, k) });

    ImageDeformation d = new ImageDeformationByFilters(
      new ImageFilter[0]);
    ImageDeformation c = new ImageDeformationByFilters(
      new ImageFilter[0]);
    ImageDeformation b = new ImageDeformationByFilters(
      new ImageFilter[0]);

    WordToImage a = new DeformedComposedWordToImage(e, 
      f, g, c, b, d);
    addFactory(new GimpyFactory(h, a));
  }
}