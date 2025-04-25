//클릭시 파일 선택 가능 
document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('file').click();
})

//파일형식, 사이즈 체크
const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$");
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmp)$");
const maxSize = 1024*1024*20;

//규칙설정
function fileValidation(name, fileSize){
    let fileName = name.toLowCase();
    if(regExp.test(fileName)){ //파일 확장자에 실행 파일 확장자가 있는 경우
        return 0;
    } else if(fileSize > maxSize){
        return 0;
    } else if(!regExpImg.test(fileName)){ //이미지 파일이 아닐 경우
        return 0;
    } else{
        return 1;
    }
}

// 첨부파일에 따라 등록이 가능한지 체크
document.addEventListener('change', (e)=>{
    
    if(e.target.id === 'file'){
        const fileObject = document.getElementById('file').files;
        console.log(">>>" + fileObject);
    
        document.getElementById('regBtn').disable = false;
    
        let div = document.getElementById('fileZone');
        div.innerHTML= '';
        let ul = `<ul class="list-group">`;
    
        
        let isOk = 1;
        for (let file of fileObject){
            let vaildResult = fileValidation(file.name, file.size); //한 파일에 대한 결과
            isOk *= vaildResult;
    
            ul += `<li class="list-group-item">`;
            ul += `<div>${vaildResult?'업로드 가능':'업로드 불가능'}</div> ${file.name} `;
            ul += `<span class="badge text-bg-${vaildResult? 'primary' : 'danger'}"> ${file.size}</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;
    
        if (isOk == 0){
            document.getElementById('regBtn').disable = true;
        }
    }
})

