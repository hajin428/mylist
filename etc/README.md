# My List Project Local 환경 구성

> 이 문서는 My List 프로젝트의 Local 개발 환경 구성을 위한 가이드입니다.  
> 문서를 참고하여 환경 구성을 완료한 뒤, 실행이 정상적으로 이루어지지 않을 경우, 아래 메인테이너에게 문의하시기 바랍니다.

## Main Tainer
- **권하진**: amm_321@naver.com

---

## 0. 선행 작업
**아래의 도구들이 로컬 환경에 설치되어 있어야 합니다.**

1. **Docker**
2. **Docker Compose**
3. **Docker Daemon or Docker Desktop**
    - Windows 또는 Mac 사용자는 Docker Desktop 설치 
    - Linux 사용자는 Docker Daemon 활성화 

---

## 1. Docker Compose 실행
```bash
# 1. /etc 경로에서 실행
cd etc
# 2. docker compose container set 실행
docker compose up -d
```
