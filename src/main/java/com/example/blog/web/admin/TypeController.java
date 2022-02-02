package com.example.blog.web.admin;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.blog.po.Type;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.GeneratedValue;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String typeList(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum, 8);
        List<Type> allType = typeService.findAllType();
        //得到分页结果对象
        PageInfo<Type> lists = new PageInfo<>(allType);
        model.addAttribute("pages", lists);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(){
        return "admin/type-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.findTypeById(id));
        return "admin/type-update";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        Type t = typeService.findTypeByName(type.getName());
        if(t == null) {
            attributes.addFlashAttribute("message", "添加分类成功！");
            typeService.addType(type);
            return "redirect:/admin/types";
        } else if(t.getName().trim().isEmpty()){
            attributes.addFlashAttribute("message", "名称不能为空");
            return "redirect:/admin/types/input";
        }
        else {
            attributes.addFlashAttribute("message", "已存在该分类，请重置！");
            return "redirect:/admin/types/input";
        }
    }

    @PostMapping("/types/update")
    public String editType(Type type, RedirectAttributes attributes){
        typeService.updateType(type);
        attributes.addFlashAttribute("message", "修改分类成功！");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";

    }

}
